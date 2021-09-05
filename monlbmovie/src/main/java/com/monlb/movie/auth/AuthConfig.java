package com.monlb.movie.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {

    @Value("${api.key.auth.header.name}")
    private String API_KEY_AUTH_HEADER_NAME;

    @Value("${api.key}")
    private String validApiKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AuthFilter filter = new AuthFilter(API_KEY_AUTH_HEADER_NAME);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (validApiKey.equals(principal)) {
                authentication.setAuthenticated(true);
                return authentication;
            }else{
                throw new BadCredentialsException("Bad ApiKey credentials");
            }
        });

        // favorites以外はAPI_KEY認証を行う
        http
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin() // localからh2-console使用
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .antMatchers("/favorites", "/h2-console/**").permitAll() // local h2
//                .antMatchers("/favorites").permitAll()
                .anyRequest().authenticated();
    }

}
