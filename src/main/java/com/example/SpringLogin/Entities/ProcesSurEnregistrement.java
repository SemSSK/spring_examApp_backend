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
    private Boolean hasCheated;
    private String observation;
    @Column(nullable = false)
    private Timestamp dateOfReview;

    @Id
    @OneToOne
    private Enregistrement enregistrement;
}
