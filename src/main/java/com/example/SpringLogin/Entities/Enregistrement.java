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
public class Enregistrement  implements Serializable{

    @EmbeddedId
    private EtudiantSessionKey enregistrementId;

    @Column(nullable = false,unique = true)
    private String url;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    @MapsId("etudiantId")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @MapsId("sessionId")
    private SessionExamen sessionExamen;

    @OneToOne(mappedBy = "enregistrement")
    private ProcesSurEnregistrement procesSurEnregistrement;
}
