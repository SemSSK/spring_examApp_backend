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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id")
    private Examen exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @OneToMany(mappedBy = "copie",fetch = FetchType.LAZY)
    private Collection<Reponse> reponses = new ArrayList<>();

    @OneToOne(mappedBy = "copie",fetch = FetchType.LAZY)
    private Reclamation reclamation;
}
