package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String typeAnswer;
    private int points;
    private String tags;
    @Column(nullable = false)
    private Timestamp dateCreation;
    private String description;
    private int nbrOccurences = 0;
    private float rating;
    private int nbrRaitings = 0;

    @ManyToMany(mappedBy = "questions")
    private Collection<Examen> examens = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @OneToMany(mappedBy = "question")
    private Collection<Reponse> reponses = new ArrayList<>();
}
