package com.example.SpringLogin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copie_id")
    @JsonIgnore
    private Copie copie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof Reponse)){
            return false;
        }

        Reponse reponse = (Reponse)obj;

        return this.reponseId.equals(reponse.reponseId);
    }

}
