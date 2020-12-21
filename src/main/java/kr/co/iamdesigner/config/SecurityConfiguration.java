package kr.co.iamdesigner.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC = new String[]{
            "/api/registrations"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .and()
                .csrf().disable();
    }
}
