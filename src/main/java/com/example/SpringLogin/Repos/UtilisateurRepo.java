package com.example.SpringLogin.Repos;

import com.example.SpringLogin.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur,Long> {
    Utilisateur findByEmail(String email);
}
