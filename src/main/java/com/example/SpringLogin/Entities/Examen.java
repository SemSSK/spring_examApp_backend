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
public class Examen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long examId;
    @Column(nullable = false)
    private Timestamp dateCreation;
    private String publicInfo;
    @Column(nullable = false)
    private Boolean isActive = false;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "examen_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Collection<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "exam")
    private Collection<Copie> copies = new ArrayList<>();


}
