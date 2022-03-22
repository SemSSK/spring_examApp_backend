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
public class SessionExamen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sessionId;

    @OneToMany(mappedBy = "sessionExamen")
    private Collection<Présences> présences = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="planning_id")
    private PlanningExamen plannings;

    @OneToMany(mappedBy = "sessionExamen")
    private Collection<Enregistrement> enregistrements = new ArrayList<>();

    @OneToOne(mappedBy = "sessionExamen")
    private ProcesSurSession procesSurSessions;

    @ManyToOne
    @JoinColumn(name = "surveillant_id")
    private Enseignant surveillant;

}
