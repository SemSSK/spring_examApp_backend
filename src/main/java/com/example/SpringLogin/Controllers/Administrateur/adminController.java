package com.example.SpringLogin.Controllers.Administrateur;

import com.example.SpringLogin.Entities.Utilisateur;
import com.example.SpringLogin.Repos.UtilisateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class adminController {
    @Autowired
    private final UtilisateurRepo utilisateurRepo;


    @GetMapping("")
    private ResponseEntity<?> sayHello(){
        return ResponseEntity.ok().body("Hello Admin");
    }


    @PostMapping("/getUsers")
    private ArrayList<Utilisateur> getUser(){
        return (ArrayList<Utilisateur>)utilisateurRepo.findAll();
    }
}
