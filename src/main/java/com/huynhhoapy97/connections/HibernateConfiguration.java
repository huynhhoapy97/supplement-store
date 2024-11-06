package com.huynhhoapy97.connections;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfiguration {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MySQLConfiguration.class);
        DataSource dataSource = applicationContext.getBean(DataSource.class);

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        sessionFactory.setHibernateProperties(properties);

        sessionFactory.setPackagesToScan("com.huynhhoapy97.models");

        return sessionFactory;
    }
}
