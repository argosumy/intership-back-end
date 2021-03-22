package com.spd.baraholka.config;

import com.spd.baraholka.login.service.GoogleOAuth2UserService;
import com.spd.baraholka.user.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Qualifier("OAuth2SuccessHandler")
    private AuthenticationSuccessHandler oauth2SuccessHandler;

//    private OAuth2UserService oAuth2UserService;

    private GoogleOAuth2UserService oAuth2UserService;

    private UserDetailsService userDetailsService;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

//    @Bean(BeanIds.AUTHENTICATION_MANAGER)
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    public SecurityConfig(@Qualifier("OAuth2SuccessHandler") AuthenticationSuccessHandler oauth2SuccessHandler, GoogleOAuth2UserService oAuth2UserService, @Qualifier("UserService") UserDetailsService userDetailsService) {
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
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
//                    .authorizationEndpoint()
//                    .baseUri("/oauth2/authorize")
//                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
//                .and()
//                .redirectionEndpoint()
//                    .baseUri("/oauth2/callback/*")
//                .and()
                .userInfoEndpoint()
                    .userService(oAuth2UserService);
//                .and()
//                .successHandler(oauth2SuccessHandler);
//                .failureHandler(oAuth2AuthenticationFailureHandler);
    }
}
