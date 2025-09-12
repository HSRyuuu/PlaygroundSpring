package com.hsryuuu.base.application.config;

import java.util.Map;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

/**
 * JPA 테이블, 컬럼 네이밍 설정 빈 등록 Configuration 으로 등록하는 방법을 찾지 못해 이 방법으로 빈 등록함..
 */
@Component
public class HibernateCustomizer implements HibernatePropertiesCustomizer {

  @Override
  public void customize(Map<String, Object> hibernateProperties) {
    hibernateProperties.put(
        AvailableSettings.PHYSICAL_NAMING_STRATEGY,
        new EntityNamingStrategy());
  }

  /**
   * JPA 테이블, 컬럼 네이밍 설정 ( MariaDB는 테이블명 대소문자를 구별한다. ) 아래 설정이 없으면, @Table 에 설정한 네이밍이 소문자로 변환되어 table 을 찾지 못한다.
   */
  static class EntityNamingStrategy implements PhysicalNamingStrategy {

    private final CamelCaseToUnderscoresNamingStrategy delegate = new CamelCaseToUnderscoresNamingStrategy();

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
      return identifier;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
      return identifier;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
      return identifier;
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
      return identifier;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
      return delegate.toPhysicalColumnName(identifier, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalTypeName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
      return PhysicalNamingStrategy.super.toPhysicalTypeName(logicalName, jdbcEnvironment);
    }
  }
}
