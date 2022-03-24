package com.example.SpringLogin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "examen_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id"))
    @JsonIgnore
    private Collection<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Copie> copies = new ArrayList<>();


}
