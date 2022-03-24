package com.example.SpringLogin.Configrations;

import com.example.SpringLogin.Repos.UtilisateurRepo;
import com.example.SpringLogin.Security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ActivationFilter activationFilter = new ActivationFilter();
        AuthorizationFilter authorizationFilter = new AuthorizationFilter(utilisateurRepo);
        RoleFilter roleFilter = new RoleFilter();

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Adding the filters
        http.addFilterAfter(activationFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(authorizationFilter, activationFilter.getClass());
        http.addFilterAfter(roleFilter, authorizationFilter.getClass());

    }

}
