package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EtudiantSessionKey implements Serializable {

    @Column(name="etudiant_id")
    private Long etudiantId;
    @Column(name="session_id")
    private Long sessionId;

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof EtudiantSessionKey)){
            return false;
        }

        EtudiantSessionKey key = (EtudiantSessionKey)obj;

        return (this.etudiantId.equals(key.etudiantId)
                && this.sessionId.equals(key.sessionId));
    }
}
