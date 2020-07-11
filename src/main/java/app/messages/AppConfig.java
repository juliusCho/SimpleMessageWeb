package app.messages;

import app.etc.SecuredPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@ComponentScans({@ComponentScan("app.messages"), @ComponentScan("app.etc")})
public class AppConfig {

    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private SecuredPropertySource sps;

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
    public DataSource dataSource() {
        log.warn(sps.getDriverClassName());
        log.warn(sps.getUrl());
        log.warn(sps.getUsername());
        log.warn(sps.getPassword());

        return DataSourceBuilder
                .create()
                .username(sps.getUsername())
                .password(sps.getPassword())
                .url(sps.getUrl())
                .driverClassName(sps.getDriverClassName())
                .build();
    }

}