package com.spd.baraholka.config;

import com.spd.baraholka.login.service.GoogleOAuth2UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationSuccessHandler oauth2SuccessHandler;

    private final GoogleOAuth2UserService oAuth2UserService;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Qualifier("OAuth2SuccessHandler") AuthenticationSuccessHandler oauth2SuccessHandler,
                          GoogleOAuth2UserService oAuth2UserService, @Qualifier("UserService") UserDetailsService userDetailsService) {
        this.oauth2SuccessHandler = oauth2SuccessHandler;
        this.oAuth2UserService = oAuth2UserService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                    .userService(oAuth2UserService)
                .and()
                .successHandler(oauth2SuccessHandler);
    }
}
