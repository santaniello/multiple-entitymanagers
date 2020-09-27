package com.example.multipleentitymanagers.repositories.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2TransactionManager",
        basePackages = {"com.example.multipleentitymanagers.repositories.h2"}
)
public class H2Config {

    @Value("${datasource.h2.url}")
    private String url;
    @Value("${datasource.h2.username}")
    private String username;
    @Value("${datasource.h2.password}")
    private String password;
    @Value("${datasource.h2.driverClassName}")
    private String driverClassName;


    @Primary
    @PersistenceContext
    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(h2DataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(additionalProperties());
        emf.setPackagesToScan("com.example.multipleentitymanagers.models.h2");
        emf.setPersistenceUnitName("H2");   // <- giving 'default' as description
        emf.afterPropertiesSet();
        return emf;
    }

    @Bean
    @Primary
    public DataSource h2DataSource(){
        return DataSourceBuilder.create()
                .url(url)
                .driverClassName(driverClassName)
                .username(username)
                .password(password)
                .build();
    }

    @Primary
    @Bean("h2TransactionManager")
    public PlatformTransactionManager h2TransactionManager(
            final @Qualifier("h2EntityManagerFactory") LocalContainerEntityManagerFactoryBean h2EntityManagerFactory) {
        return new JpaTransactionManager(h2EntityManagerFactory.getObject());
    }


    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return hibernateProperties;
    }

}
