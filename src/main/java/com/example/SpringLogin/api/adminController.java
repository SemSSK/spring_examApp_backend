package com.example.SpringLogin.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.SpringLogin.Entities.Utilisateur;
import com.example.SpringLogin.Repos.UtilisateurRepo;
import com.example.SpringLogin.Security.JWTHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class adminController {
    @Autowired
    private final UtilisateurRepo utilisateurRepo;


    @GetMapping("")
    private ResponseEntity<String> sayHello(@RequestAttribute(name = "User") Utilisateur user){
        return ResponseEntity.ok().body("Hello " + user.getEmail() + user.getPassword());
    }

    @PostMapping("/write")
    private ResponseEntity<String> write(){
        return ResponseEntity.ok().body("Writing...");
    }

    @GetMapping("/getUser")
    private ResponseEntity<Utilisateur> getUser(@RequestParam String email){
        Utilisateur user = utilisateurRepo.findByEmail(email);

        if(user == null)
        {
            return new ResponseEntity<Utilisateur>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(user);
    }
}
