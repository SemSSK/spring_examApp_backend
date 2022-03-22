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
public class Administrateur extends Utilisateur{

    @Column(nullable = false)
    private int privilege;

    @OneToMany(mappedBy = "admin")
    private Collection<Utilisateur> createdUsers = new ArrayList<>();

    @OneToMany(mappedBy = "admin")
    private Collection<PlanningExamen> createdPlans = new ArrayList<>();

    @OneToMany(mappedBy = "admin")
    private Collection<Module> createdModules = new ArrayList<>();

}
