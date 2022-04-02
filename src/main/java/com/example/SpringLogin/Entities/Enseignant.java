package com.example.SpringLogin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "enseignant",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "enseignant",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<AffectationModule> affectationModules = new ArrayList<>();

    @OneToMany(mappedBy = "surveillant",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<SessionExamen> sessionExamens = new ArrayList<>();


    @Override
    public boolean equals(Object obj)
    {
        if(obj == this){
            return true;
        }

        if(!(obj instanceof Enseignant)){
            return false;
        }

        Enseignant enseignant = (Enseignant) obj;

        return this.getUserId().equals(enseignant.getUserId());
    }
}
