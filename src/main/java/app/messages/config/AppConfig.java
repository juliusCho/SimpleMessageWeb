package app.messages.config;

import app.etc.SecuredPropertySource;
import app.messages.web.AuditingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@ComponentScans({@ComponentScan("app.messages"), @ComponentScan("app.etc")})
@EnableTransactionManagement
public class AppConfig {

    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private SecuredPropertySource sps;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .username(sps.getUsername())
                .password(sps.getPassword())
                .url(sps.getUrl())
                .driverClassName(sps.getDriverClassName())
                .build();
    }

    @Bean
    public FilterRegistrationBean<AuditingFilter> auditingFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuditingFilter> registrationBean = new FilterRegistrationBean<>();
        AuditingFilter filter = new AuditingFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(Integer.MAX_VALUE);
        registrationBean.setUrlPatterns(Arrays.asList("/messages/*"));
        return registrationBean;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(this.dataSource());
        sessionFactoryBean.setPackagesToScan("app.messages");
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(this.sessionFactoryBean().getObject());
        return transactionManager;
    }

}