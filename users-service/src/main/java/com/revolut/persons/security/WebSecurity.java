package com.revolut.persons.security;

import com.revolut.persons.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder encoder;
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").hasIpAddress("192.168.0.103").and().addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(encoder);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, authenticationManager());
        authenticationFilter.setAuthenticationManager(authenticationManager());
//        authenticationFilter.setFilterProcessesUrl("/{myCustomUrlForLogin}");
        return authenticationFilter;
    }

}
