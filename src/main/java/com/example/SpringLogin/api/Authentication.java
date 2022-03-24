package com.example.SpringLogin.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.SpringLogin.Entities.Utilisateur;
import com.example.SpringLogin.Security.JWTHandler;
import com.example.SpringLogin.Services.EmailService;
import com.example.SpringLogin.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/auth")
public class Authentication {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    private final JWTHandler jwtHandler = new JWTHandler();
    public static final Map<String,Utilisateur> activationUserMap = new HashMap<>();

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Utilisateur user)
    {
        Utilisateur realUser = userService.findUserByEmail(user.getEmail());

        if(realUser == null) {
            Map<String,String> error = new HashMap<>();
            error.put("Error","User Not Found");
            return new ResponseEntity<Map<String,String>>(error, HttpStatus.NOT_FOUND);
        }
        if(!realUser.getPassword().equals(user.getPassword()))
        {
            Map<String,String> error = new HashMap<>();
            error.put("Error","Wrong password or email");
            return new ResponseEntity<Map<String,String>>(error, HttpStatus.NOT_FOUND);
        }

        //Making the token
        String accessToken = jwtHandler.generateActivationToken(realUser);
        Map<String,String> response = new HashMap<>();
        response.put("access_token",accessToken);
        response.put("activation_code","1");
        activationUserMap.put("1",realUser);
        return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);

    }

    @PostMapping("/activation")
    public ResponseEntity<Map<String,String>> activate(@RequestAttribute Utilisateur user){
        Map<String,String> response = jwtHandler.generateAuthToken(user);
        return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
    }


    @PostMapping("/refresh")
    public ResponseEntity<Map<String,String>> refresh(@RequestBody Map<String,String> refreshToken)
    {
        if(refreshToken == null){
            Map<String,String> error = new HashMap<>();
            error.put("Error","Forbidden");
            return new ResponseEntity<Map<String,String>>(error, HttpStatus.FORBIDDEN);
        }

        try {
            Utilisateur user = jwtHandler.parseAuthorizationToken(refreshToken.get("refresh_token"));
            Utilisateur realUser = userService.findUserByEmail(user.getEmail());

            if(user.getUserRole().equals("NOT_ACTIVATED")){
                Map<String,String> error = new HashMap<>();
                error.put("Error","Forbidden");
                return new ResponseEntity<Map<String,String>>(error, HttpStatus.FORBIDDEN);
            }
            if(realUser == null){
                Map<String,String> error = new HashMap<>();
                error.put("Error","Error");
                return new ResponseEntity<Map<String,String>>(error, HttpStatus.FORBIDDEN);
            }

            String accessToken = jwtHandler.refreshToken(realUser);

            Map<String,String> response = new HashMap<>();
            response.put("jwt_token",accessToken);
            return new ResponseEntity<Map<String,String>>(response,HttpStatus.OK);
        }
        catch(Exception e){
            Map<String,String> error = new HashMap<>();
            error.put("Error","Forbidden");
            return new ResponseEntity<Map<String,String>>(error, HttpStatus.FORBIDDEN);
        }


    }



}
