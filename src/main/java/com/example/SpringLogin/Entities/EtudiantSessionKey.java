package com.example.SpringLogin.Entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class EtudiantSessionKey implements Serializable {

    @Column(name="etudiant_id")
    private Long etudiantId;
    @Column(name="session_id")
    private Long sessionId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((etudiantId == null) ? 0 : etudiantId.hashCode() );
        result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
        return result;
    }


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
