package com.example.SpringLogin.Security;

import com.example.SpringLogin.Entities.Utilisateur;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EtudiantFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String direction = req.getServletPath();

        if(!direction.startsWith("/etudiant")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else{

            Utilisateur user = (Utilisateur)req.getAttribute("User");
            if(!user.getUserRole().equals("ETUDIANT")){
                res.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }
}
