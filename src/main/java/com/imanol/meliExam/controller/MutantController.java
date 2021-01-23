package com.imanol.meliExam.controller;

import com.imanol.meliExam.model.DnaModel;
import com.imanol.meliExam.model.StatsQueryModel;
import com.imanol.meliExam.repositories.Dna;
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
import java.util.List;
import java.util.Map;

@RestController
public class MutantController {

    @Autowired
    private DnaRepository repository;

    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant(@RequestBody DnaModel dna) {
        String id = String.join("", dna.getDna());
        boolean mutant = Mutant.isMutant(dna.getDna());
        if (!repository.existsById(id)) {
            repository.save(new Dna(id, mutant));
        }
        if (mutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        List<Object[]> queryResult = repository.getMutantCounts();
        Map<Boolean, Long> mappedResult = new HashMap<>();

        for (Object[] obj : queryResult) {
            Boolean isMutant = (Boolean) obj[0];
            Long count = (Long) obj[1];
            mappedResult.put(isMutant, count);
        }

        Map<String, Object> response = new HashMap<>();
        Long count_mutant_dna = mappedResult.get(true);
        Long count_human_dna = mappedResult.get(false);
        double ratio =  count_mutant_dna.doubleValue() / count_human_dna.doubleValue();

        response.put("count_mutant_dna", count_mutant_dna);
        response.put("count_human_dna", count_human_dna);
        response.put("ratio", ratio);
        return ResponseEntity.ok(response);
    }

}
