package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reponseId;
    private String content;
    private float points;

    @ManyToOne
    @JoinColumn(name = "copie_id")
    private Copie copie;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
