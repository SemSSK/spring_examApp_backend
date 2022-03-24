package com.example.SpringLogin.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.SpringLogin.Entities.Utilisateur;
import org.springframework.security.crypto.keygen.Base64StringKeyGenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTHandler {

    private final static String secretActivation =  new Base64StringKeyGenerator().generateKey();
    private final static String secretAuthorization = new Base64StringKeyGenerator().generateKey();

    public String generateActivationToken(Utilisateur user){
        Algorithm algorithm = Algorithm.HMAC256(secretActivation.getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role","NOT_ACTIVATED")
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 1000))
                .sign(algorithm);
        return accessToken;
    }

    public String refreshToken(Utilisateur user){
        Algorithm algorithm = Algorithm.HMAC256(secretAuthorization.getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role",user.getUserRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 1000))
                .sign(algorithm);
        return accessToken;
    }

    public Map<String,String > generateAuthToken(Utilisateur user){

        Algorithm algorithm = Algorithm.HMAC256(secretAuthorization.getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getEmail())
                .withClaim("role",user.getUserRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 1000))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getEmail())
                .sign(algorithm);

        Map<String,String> tokens = new HashMap<>();
        tokens.put("jwt_token",accessToken);
        tokens.put("refresh_token",refreshToken);
        return tokens;
    }

    public Utilisateur parseActivationToken(String token){
        return parseToken(token,secretActivation);
    }
    public Utilisateur parseAuthorizationToken(String token){
        return parseToken(token,secretAuthorization);
    }

    private Utilisateur parseToken(String token,String secret){

        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        String role = decodedJWT.getClaim("role").asString();
        Utilisateur user = new Utilisateur();
        user.setEmail(username);
        user.setUserRole(role);
        return user;
    }

}
