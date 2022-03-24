package com.example.SpringLogin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanningExamen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planId;

    @Column(nullable = false)
    private Timestamp dateOfExame;
    @Column(nullable = false)
    private Time duration;
    @Column(nullable = false,unique = true)
    private String codeSurveillant;
    @Column(nullable = false,unique = true)
    private String codeEtudiant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id")
    private Administrateur admin;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "etudiant_id"))
    @JsonIgnore
    private Collection<Etudiant> etudiants = new ArrayList<>();

    @OneToMany(mappedBy = "plannings",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<SessionExamen> sessionExamens = new ArrayList<>();
}
