package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant extends Utilisateur {

    @Column(nullable = false)
    private String niveau;
    @Column(nullable = false)
    private int section;
    @Column(nullable = false)
    private int groupe;

    @OneToMany(mappedBy="etudiant")
    private Collection<Copie> copies = new ArrayList<>();

    @OneToMany(mappedBy = "etudiant")
    private Collection<Enregistrement> enregistrements = new ArrayList<>();

    @ManyToMany(mappedBy = "etudiants")
    private Collection<PlanningExamen> planningExamens = new ArrayList<>();
}
