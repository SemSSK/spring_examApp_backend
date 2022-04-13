package com.example.SpringLogin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long claimId;

    @Column(nullable = false)
    private String claimType;

    @Column(nullable = false)
    private String subject;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copie_id")
    private Copie copie;

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof Reclamation)){
            return false;
        }

        Reclamation reclamation = (Reclamation)obj;

        return this.claimId.equals(reclamation.claimId);
    }

}
