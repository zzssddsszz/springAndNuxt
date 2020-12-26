package kr.co.iamdesigner.config;

import kr.co.iamdesigner.domain.common.security.AccessDeniedHandlerImpl;
import kr.co.iamdesigner.domain.common.security.ApiRequestAccessDeniedExceptionTranslationFilter;
import kr.co.iamdesigner.web.apis.authenticate.AuthenticationFilter;
import kr.co.iamdesigner.web.apis.authenticate.SimpleAuthenticationFailureHandler;
import kr.co.iamdesigner.web.apis.authenticate.SimpleAuthenticationSuccessHandler;
import kr.co.iamdesigner.web.apis.authenticate.SimpleLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String[] PUBLIC = new String[]{
            "/error", "/login", "/logout", "/register", "/api/registrations"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(apiRequestExceptionTranslationFilter(), ExceptionTranslationFilter.class)
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutUrl("/api/me/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleLogoutSuccessHandler();
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    public ApiRequestAccessDeniedExceptionTranslationFilter apiRequestExceptionTranslationFilter() {
        return new ApiRequestAccessDeniedExceptionTranslationFilter();
    }
}
