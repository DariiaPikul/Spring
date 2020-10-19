package com.spring.intro.config;

import com.spring.intro.model.User;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {
        "com.spring.intro.service",
        "com.spring.intro.dao"
})
public class AppConfig {
    private static final String HB_SHOW_SQL = "hibernate.show_sql";
    private static final String HB_FORMAT_SQL = "hibernate.format_sql";
    private static final String HB_HBM_2_DDL_AUTO = "hibernate.hbm2ddl.auto";
    @Autowired
    private Environment environment;

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("db.driver"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(getDataSource());
        Properties properties = new Properties();
        properties.put(HB_SHOW_SQL, environment.getProperty(HB_SHOW_SQL));
        properties.put(HB_FORMAT_SQL, environment.getProperty(HB_FORMAT_SQL));
        properties.put(HB_HBM_2_DDL_AUTO, environment.getProperty(HB_HBM_2_DDL_AUTO));
        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(User.class);
        return factoryBean;
    }
}
