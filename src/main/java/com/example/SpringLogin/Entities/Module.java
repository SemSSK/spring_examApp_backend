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
    @JsonIgnore
    private Administrateur admin;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "module")
    @JsonIgnore
    private Collection<Question> questions;

    @OneToMany(mappedBy = "module",fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<AffectationModule> affectationModules = new ArrayList<>();

    @OneToOne(mappedBy = "module",fetch = FetchType.LAZY)
    @JsonIgnore
    private Examen examen;

    @Override
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }

        if(!(obj instanceof Module)){
            return false;
        }

        Module module = (Module) obj;

        return this.id.equals(module.id);
    }
}
