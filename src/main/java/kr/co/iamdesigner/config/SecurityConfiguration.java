package kr.co.iamdesigner.config;

import kr.co.iamdesigner.domain.common.security.AccessDeniedHandlerImpl;
import kr.co.iamdesigner.domain.common.security.ApiRequestAccessDeniedExceptionTranslationFilter;
import kr.co.iamdesigner.web.apis.authenticate.AuthenticationFilter;
import kr.co.iamdesigner.web.apis.authenticate.SimpleLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

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
                .addFilterAfter(apiRequestExceptionTranslationFilter())
                .formLogin()
                .loginPage("/login")
              .and()
                .logout()
                .logoutUrl("/api/me/logout")
                .logoutSuccessHandler(logoutSuccessHandler())

                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/js/**", "/css/**", "/images/**", "/favicon.ico");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    private AuthenticationFilter authenticationFilter() {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailurHandler());
    }

    @Bean
    private AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    private AuthenticationFailureHandler authenticationFailurHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    private LogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleLogoutSuccessHandler();
    }

    private AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    private ApiRequestAccessDeniedExceptionTranslationFilter apiRequestExceptionTranslationFilter() {
    }
}
