package com.hsryuuu.base.sample.transactional;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TxConfig {
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);// Savepoint 지원
        dataSourceTransactionManager.setNestedTransactionAllowed(true);
        return dataSourceTransactionManager; // Savepoint 지원
    }
}