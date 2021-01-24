package com.imanol.meliExam.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DnaRepository extends CrudRepository<Dna, String> {
    @Query("SELECT dna.mutant, COUNT(*) FROM Dna dna GROUP BY dna.mutant")
    List<Object[]> getMutantCounts();
}
