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
import java.util.ArrayList;

public class RoleFilter extends GenericFilterBean {

    private ArrayList<String> roleList;
    public RoleFilter(){

        super();
        roleList = new ArrayList<>();
        roleList.add("ADMIN");
        roleList.add("ETUDIANT");
        roleList.add("ENSEIGNANT");

    }

    public boolean checkifCorrectDirection(String direction){
        return direction.startsWith("/admin") || direction.startsWith("/etudiant") || direction.startsWith("/enseignant");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String direction = req.getServletPath();


        if(!checkifCorrectDirection(direction)){

            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            int testingRole;

            if(direction.startsWith("/admin")){
                testingRole = 0;
            }
            else if(direction.startsWith("/etudiant")){
                testingRole = 1;
            }
            else{
                testingRole = 2;
            }

            System.out.println(roleList.get(testingRole));

            Utilisateur user = (Utilisateur)req.getAttribute("User");
            if(!user.getUserRole().equals(roleList.get(testingRole))){
                System.out.println("Blocked by role filter");
                res.sendError(HttpServletResponse.SC_FORBIDDEN);

            }
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }


}
