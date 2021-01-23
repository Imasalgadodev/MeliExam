package com.imanol.meliExam.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;

public interface DnaRepository extends CrudRepository<DnaEntity, String> {
    @Query("SELECT mutant, COUNT(*) FROM dna BY mutant")
    Map<Boolean, Integer> getMutantCounts();
}
