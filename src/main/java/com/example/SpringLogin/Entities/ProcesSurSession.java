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
    private Boolean notify;

    @Id
    @OneToOne
    private SessionExamen sessionExamen;


}
