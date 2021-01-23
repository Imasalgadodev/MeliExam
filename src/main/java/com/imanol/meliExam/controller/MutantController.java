package com.imanol.meliExam.controller;

import com.imanol.meliExam.model.Dna;
import com.imanol.meliExam.repositories.DnaEntity;
import com.imanol.meliExam.repositories.DnaRepository;
import com.imanol.meliExam.utils.Mutant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MutantController {

    @Autowired
    private DnaRepository repository;

    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant (@RequestBody Dna dna){
        String id = String.join("", dna.getDna());
        boolean mutant = Mutant.isMutant(dna.getDna());
        if(!repository.existsById(id)) {
            repository.save(new DnaEntity(id, mutant));
        }
        if(mutant){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String,Object>> getStats(){
        Map<Boolean, Integer> counts = repository.getMutantCounts();
        Map<String,Object> response = new HashMap<>();
        Integer count_mutant_dna = counts.get(true);
        Integer count_human_dna = counts.get(false);
        response.put("count_mutant_dna", count_mutant_dna);
        response.put("count_human_dna", count_human_dna);
        response.put("ratio", count_human_dna / count_mutant_dna);
        return ResponseEntity.ok(response);
    }
}
