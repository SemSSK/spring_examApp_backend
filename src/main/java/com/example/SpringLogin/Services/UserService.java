package com.example.SpringLogin.Services;

import com.example.SpringLogin.Entities.Utilisateur;
import com.example.SpringLogin.Repos.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService {
    @Autowired
    private UtilisateurRepo utilisateurRepo;

    public Utilisateur findUserByEmail(String email){
        return utilisateurRepo.findByEmail(email);
    }
}
