package com.example.SpringLogin.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EtudiantSessionKey implements Serializable {
    @Column(name="etudiant_id")
    private Long etudiantId;
    @Column(name="session_id")
    private Long sessionId;
}
