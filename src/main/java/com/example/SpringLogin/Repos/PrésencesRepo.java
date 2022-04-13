package com.example.SpringLogin.Repos;

import com.example.SpringLogin.Entities.EtudiantSessionKey;
import com.example.SpringLogin.Entities.Présences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrésencesRepo extends JpaRepository<Présences, EtudiantSessionKey> {
}
