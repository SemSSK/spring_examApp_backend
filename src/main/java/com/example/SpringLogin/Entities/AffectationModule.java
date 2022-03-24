package com.example.SpringLogin.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
class AffectationModuleKey implements Serializable{
    @Column(name = "enseignant_Id")
    private Long enseignantId;
    @Column(name = "module_Id")
    private Long moduleId;
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
