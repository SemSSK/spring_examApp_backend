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
public class JustificationAbsence implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private Présences présences;

    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String description;

}
