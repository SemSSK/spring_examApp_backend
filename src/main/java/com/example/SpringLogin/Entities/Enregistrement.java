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
public class Enregistrement  implements Serializable{

    @EmbeddedId
    private EtudiantSessionKey enregistrementId;

    @Column(nullable = false,unique = true)
    private String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "etudiant_id")
    @MapsId("etudiantId")
    private Etudiant etudiant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id")
    @MapsId("sessionId")
    private SessionExamen sessionExamen;

    @OneToOne(mappedBy = "enregistrement",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private ProcesSurEnregistrement procesSurEnregistrement;


    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(!(obj instanceof Enregistrement)){
            return false;
        }

        Enregistrement enregistrement = (Enregistrement) obj;

        return this.enregistrementId.equals(enregistrement.enregistrementId);
    }
}
