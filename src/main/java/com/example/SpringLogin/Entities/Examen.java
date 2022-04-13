package com.example.SpringLogin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
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
    private boolean isActive = false;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "examen_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id"))
    private Collection<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "exam",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<Copie> copies = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private Module module;

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(!(obj instanceof Examen)){
            return false;
        }

        Examen examen = (Examen) obj;

        return this.examId.equals(examen.examId);
    }
}
