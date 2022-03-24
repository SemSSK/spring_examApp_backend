package com.example.SpringLogin.Security;

import com.example.SpringLogin.Entities.Utilisateur;
import com.example.SpringLogin.Repos.UtilisateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AuthorizationFilter  extends GenericFilterBean {

    private final JWTHandler jwtHandler = new JWTHandler();

    @Autowired
    private final UtilisateurRepo utilisateurRepo;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String direction = req.getServletPath();

        if(direction.equals("/auth/login") || direction.equals("/auth/activation")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else
        {
            try {
                String token = req.getHeader("token");
                Utilisateur user = jwtHandler.parseAuthorizationToken(token);
                Utilisateur userAfter = utilisateurRepo.findByEmail(user.getEmail());
                req.setAttribute("User",userAfter);
                filterChain.doFilter(servletRequest,servletResponse);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }
}
