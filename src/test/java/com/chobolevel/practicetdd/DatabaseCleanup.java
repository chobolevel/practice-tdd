package com.chobolevel.practicetdd;

import com.google.common.base.CaseFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// 테스트 격리를 위한 클래스
@Component
public class DatabaseCleanup implements InitializingBean {

    @PersistenceContext
    private EntityManager em;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        final Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        tableNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e -> e.getJavaType().getAnnotation(Table.class).name())
                .collect(Collectors.toList());

        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && !hasTableAnnotation(e))
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .toList();
        tableNames.addAll(entityNames);
    }

    private boolean isEntity(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Entity.class);
    }

    private boolean hasTableAnnotation(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    @Transactional
    public void execute() {
        em.flush();
        // 참조 무결성
        // FK 연결된 테이블이 있을 때 레코드 삭제가 안되는 경우를 위해 잠시 설정 끄기
        em.createNativeQuery("SET foreign_key_checks = 0;").executeUpdate();

        for(final String tableName : tableNames) {
            // 테스트한 테이블의 모든 레코드를 삭제
            em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
        }

        // 참조 무결성 옵션 켜기
        em.createNativeQuery("SET foreign_key_checks = 1;").executeUpdate();
    }
}
