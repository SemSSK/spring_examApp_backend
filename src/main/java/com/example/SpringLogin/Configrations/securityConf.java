package com.example.SpringLogin.Configrations;

import com.example.SpringLogin.LogInWork.CustomUserDetailService;
import com.example.SpringLogin.LogInWork.CustomUserDetails;
import com.example.SpringLogin.LogInWork.FirstAuthProvider;
import com.example.SpringLogin.Repos.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class securityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Autowired
    private FirstAuthProvider firstAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/etudiant").hasRole("ETUDIANT")
                .antMatchers("/enseignant").hasRole("ENSEIGNANT")
                .antMatchers("/activate").hasRole("NOT_ACTIVATED")
                .and().formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(firstAuthProvider)
                .userDetailsService(new CustomUserDetailService(utilisateurRepo));
    }


    @Bean
    public PasswordEncoder PasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


}
