package com.udacity.jdnd.course1.config;

import com.udacity.jdnd.course1.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/signup","/css/**", "/js/**").permitAll().
                anyRequest().authenticated().
                and().
                formLogin().
                loginPage("/login").
                permitAll().
                and().
                logout().
                permitAll();

        http.formLogin().
                defaultSuccessUrl("/chat", true);

        http.logout().
                logoutSuccessUrl("/login").
                logoutUrl("/logout").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
