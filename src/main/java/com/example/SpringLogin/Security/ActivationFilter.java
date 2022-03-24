package com.example.SpringLogin.Security;

import com.example.SpringLogin.Entities.Utilisateur;
import com.example.SpringLogin.api.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActivationFilter  extends GenericFilterBean {

    private final JWTHandler jwtHandler = new JWTHandler();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String direction = req.getServletPath();

        if(!direction.equals("/auth/activation")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{
            String activationCode = req.getHeader("activation");
            String token = req.getHeader("token");

            if(activationCode == null || token == null)
            {
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
            try {
                Utilisateur user = jwtHandler.parseActivationToken(token);

                if (!Authentication.activationUserMap.containsKey(activationCode)) {
                    res.sendError(HttpServletResponse.SC_FORBIDDEN);
                }

                Utilisateur realUser = Authentication.activationUserMap.get(activationCode);

                if (!user.getEmail().equals(realUser.getEmail())) {
                    Map<String, String> error = new HashMap<>();
                    error.put("Error", "Forbidden");
                    res.sendError(HttpServletResponse.SC_FORBIDDEN);
                }

                Authentication.activationUserMap.remove(activationCode);
                req.setAttribute("user", realUser);
                filterChain.doFilter(servletRequest, servletResponse);
            }
            catch(Exception e){
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }
}
