package io.github.Victor5H.config;

import io.github.Victor5H.dao.StudentDaoImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("io.github.Victor5H")
public class JavaConfig {

    @Bean("template")
    public HibernateTemplate getTemplate() {
        HibernateTemplate hibernateTemplate = new HibernateTemplate();
        hibernateTemplate.setSessionFactory(getSessionFactoryBean().getObject());
        return hibernateTemplate;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springorm");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean("studentDao")
    public StudentDaoImpl getStudentDao() {
        StudentDaoImpl studentDao = new StudentDaoImpl(getTemplate());

        return studentDao;
    }

    @Bean("sessionfactory")
    public LocalSessionFactoryBean getSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        settings data source 1
        localSessionFactoryBean.setDataSource(getDataSource());

//        set package where entities with JPA annotations are present 2
        localSessionFactoryBean.setPackagesToScan("io.github.Victor5H");
        Properties properties = new Properties();
        // to print all sql statements generated by hibernate
        properties.put("hibernate.show_sql", "true");
//        this will create the table if not present, and alter table if fields are not present as mentioned in entity
        properties.put("hibernate.hbm2ddl.auto", "update");

//        3
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        SessionFactory factory = getSessionFactoryBean().getObject();
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(factory);
        return transactionManager;
    }
}
