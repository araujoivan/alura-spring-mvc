/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.system.configuration;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * 
 * @author eder
 */

// this annotation ask Spring to take care of database transactions
@EnableTransactionManagement
public class JPAConfiguration {
    
    //error -> there is no bean of type Plataform transaction manager
    // solution with this method bellow
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory manager) {
        return new JpaTransactionManager(manager);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        
        final JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        
        final DriverManagerDataSource source = new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/codehouse", "root", "12345678");
        
        source.setDriverClassName("com.mysql.jdbc.Driver");
        
        factory.setJpaVendorAdapter(adapter);    
        factory.setDataSource(source);
        
        final  Properties props = new Properties();
        
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.show_sql", "true");
        // everytime we change our model classes hibernate changes database schema.
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        
        factory.setJpaProperties(props);
        
        factory.setPackagesToScan("br.com.system.model");
        
        return factory;
    }
    
}
