package com.example.SpringLogin.api;

import com.example.SpringLogin.Entities.Utilisateur;
import com.example.SpringLogin.LogInWork.CustomUserDetails;
import com.example.SpringLogin.Repos.UtilisateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class adminController {
    @Autowired
    private final UtilisateurRepo utilisateurRepo;


    @GetMapping("")
    private ResponseEntity<?> sayHello(){
        CustomUserDetails user = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(user.getAuthorities());
    }


    @PostMapping("/getUsers")
    private ArrayList<Utilisateur> getUser(){
        return (ArrayList<Utilisateur>)utilisateurRepo.findAll();
    }
}
