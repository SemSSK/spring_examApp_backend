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
public class Enseignant extends Utilisateur {
    @Column(nullable = false)
    private String grade;

    @OneToMany(mappedBy = "enseignant")
    private Collection<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "enseignant")
    private Collection<AffectationModule> affectationModules = new ArrayList<>();

    @OneToMany(mappedBy = "surveillant")
    private Collection<SessionExamen> sessionExamens = new ArrayList<>();
}
