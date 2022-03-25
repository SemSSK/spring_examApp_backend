package com.example.SpringLogin.LogInWork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class FirstAuthProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(email.isEmpty()){
            throw new BadCredentialsException("invalid login details");
        }
        CustomUserDetails user = null;
        try{
            user = (CustomUserDetails)customUserDetailService.loadUserByUsername(email);
            if(!user.getUtilisateur().getPassword().equals(password)){
                throw new BadCredentialsException("invalid login details");
            }
            String code = new Base64StringKeyGenerator().generateKey();
            System.out.println(code);
            ActivationCode activation_code = new ActivationCode(code);
            ActivationCode.codesMap.put(email,activation_code);
            return new UsernamePasswordAuthenticationToken(user,authentication.getCredentials(),user.getAuthorities());

        }catch(UsernameNotFoundException e) {
            throw new BadCredentialsException("invalid login details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
