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
public class Présences implements Serializable{

    @EmbeddedId
    private EtudiantSessionKey PrésenceId;

    private String state;
    private String justification;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    @MapsId("etudiantId")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "sessionexamen_id")
    @MapsId("sessionId")
    private SessionExamen sessionExamen;

    @OneToOne(mappedBy = "présences")
    private JustificationAbsence justificationAbsence;
}
