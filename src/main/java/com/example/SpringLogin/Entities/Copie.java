package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Copie  implements Serializable {
    @Id
    private Long copieId;
    private String observation;
    private float note;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Examen exam;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @OneToMany(mappedBy = "copie")
    private Collection<Reponse> reponses = new ArrayList<>();

    @OneToOne(mappedBy = "copie")
    private Reclamation reclamation;
}
