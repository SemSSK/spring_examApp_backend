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
public class Reclamation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long claimId;

    @Column(nullable = false)
    private String claimType;

    @Column(nullable = false)
    private String subject;

    @OneToOne
    @JoinColumn(name = "copie_id")
    private Copie copie;
}
