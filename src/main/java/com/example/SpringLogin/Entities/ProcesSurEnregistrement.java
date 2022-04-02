package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcesSurEnregistrement implements Serializable {

    @Column(nullable = false)
    private boolean hasCheated;
    private String observation;
    @Column(nullable = false)
    private Timestamp dateOfReview;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private Enregistrement enregistrement;

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof ProcesSurEnregistrement)){
            return false;
        }

        ProcesSurEnregistrement procesSurEnregistrement = (ProcesSurEnregistrement)obj;

        return this.enregistrement.equals(procesSurEnregistrement.enregistrement);
    }

}
