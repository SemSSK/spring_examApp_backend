package com.example.SpringLogin.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class SessionExamen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;

    @OneToMany(mappedBy = "sessionExamen",fetch = FetchType.LAZY)
    private Collection<Présences> présences = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="planning_id")
    private PlanningExamen plannings;

    @OneToMany(mappedBy = "sessionExamen",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Enregistrement> enregistrements = new ArrayList<>();

    @OneToOne(mappedBy = "sessionExamen",fetch = FetchType.LAZY)
    private ProcesSurSession procesSurSessions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surveillant_id")
    private Enseignant surveillant;


    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof SessionExamen)){
            return false;
        }

        SessionExamen sessionExamen = (SessionExamen)obj;

        return this.sessionId.equals(sessionExamen.sessionId);
    }

}
