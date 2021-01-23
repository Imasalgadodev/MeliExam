package com.imanol.meliExam.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;

public interface DnaRepository extends CrudRepository<Dna, String> {
    @Query("SELECT mutant, COUNT(*) FROM Dna GROUP BY mutant")
    Map<Boolean, Integer> getMutantCounts();
}
