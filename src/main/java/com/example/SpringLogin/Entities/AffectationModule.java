package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
class AffectationModuleKey implements Serializable{
    @Column(name = "enseignant_Id")
    private Long enseignantId;
    @Column(name = "module_Id")
    private Long moduleId;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((enseignantId == null) ? 0 : enseignantId.hashCode() );
        result = prime * result + ((moduleId == null) ? 0 : moduleId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }

        if(!(obj instanceof AffectationModuleKey))
        {
            return false;
        }

        AffectationModuleKey key = (AffectationModuleKey) obj;

        return (key.enseignantId.equals(this.enseignantId)
                && key.moduleId.equals(this.moduleId));
    }
}


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectationModule  implements Serializable {

    @EmbeddedId
    private AffectationModuleKey affectationModuleId;
    @Column(name = "valueType")
    private String type;
    private Timestamp affectationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enseignant_id")
    @MapsId("enseignantId")
    private Enseignant enseignant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    @MapsId("moduleId")
    private Module module;
}
