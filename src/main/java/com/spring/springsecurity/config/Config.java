package com.spring.springsecurity.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.spring.springsecurity")
@PropertySource("classpath:persistance-postgres.properties")
public class Config {

    @Autowired
    private Environment env;

    private Logger logger = Logger.getLogger(getClass().getName());

    // define bean for View Resolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public DataSource securityDataSource(){

        ComboPooledDataSource source = new ComboPooledDataSource();
        try {
            source.setDriverClass(env.getProperty("jdbc.driver"));
        }catch (PropertyVetoException exe){
            throw new RuntimeException(exe);
        }
        logger.info(">>> jdbc.url="+env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user="+env.getProperty("jdbc.user"));

        source.setJdbcUrl(env.getProperty("jdbc.url"));
        source.setUser(env.getProperty("jdbc.user"));
        source.setPassword(env.getProperty("jdbc.password"));

        source.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        source.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        source.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        source.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return source;
    }
    private int getIntProperty(String prop) {
        return Integer.parseInt(Objects.requireNonNull(env.getProperty(prop)));
    }
}
