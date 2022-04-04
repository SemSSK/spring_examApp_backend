package com.example.SpringLogin.Configrations.SecurityServices;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class ContextHandlerClass {

    public ContextHandlerClass(){
        System.out.println("ContextHandlerClass instatiated");
    }

    private Object getPrincipal(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public CustomUserDetails getCurrentLoggedInUser() {
        Object principal = getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails)principal;
        return userDetails;
    }

    public void setCurrentLoggedInUserAuthorities(){
        CustomUserDetails user = getCurrentLoggedInUser();
        user.setAuthority("ROLE_" + user.getUtilisateur().getUserRole());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user,auth.getCredentials(),user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }


}
