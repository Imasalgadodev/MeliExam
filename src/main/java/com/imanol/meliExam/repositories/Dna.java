package com.imanol.meliExam.repositories;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Dna implements Serializable {

    @Id
    private String id;

    @Column(name = "mutant")
    private boolean mutant;

    public Dna(){}

    public Dna(String id, boolean mutant) {
        this.id = id;
        this.mutant = mutant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
