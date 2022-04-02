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
public class ProcesSurSession implements Serializable {

    private String obsevation;

    @Column(nullable = false)
    private boolean notify;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private SessionExamen sessionExamen;

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof ProcesSurSession)){
            return false;
        }

        ProcesSurSession procesSurSession = (ProcesSurSession)obj;

        return this.sessionExamen.equals(procesSurSession.sessionExamen);
    }


}
