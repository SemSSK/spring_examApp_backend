package com.example.SpringLogin.api;

import com.example.SpringLogin.Repos.UtilisateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/enseignant")
@AllArgsConstructor
public class enseignantController {

    @Autowired
    private final UtilisateurRepo utilisateurRepo;

    @GetMapping("")
    private ResponseEntity<String> sayHello(){
        return ResponseEntity.ok().body("Hello Enseignant");
    }
}
