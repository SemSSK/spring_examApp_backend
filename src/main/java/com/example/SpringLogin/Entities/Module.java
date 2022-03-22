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
public class Module implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nomModule;
    @Column(nullable = false)
    private int coefficient;
    @Column(nullable = false)
    private String niveau;
    @Column(nullable = false)
    private boolean hasTDTP;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private Administrateur admin;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "module")
    private Collection<Question> questions;

    @OneToMany(mappedBy = "module")
    private Collection<AffectationModule> affectationModules = new ArrayList<>();
}
