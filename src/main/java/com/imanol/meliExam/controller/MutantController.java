package com.imanol.meliExam.controller;

import com.imanol.meliExam.model.Dna;
import com.imanol.meliExam.utils.Mutant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {
    @PostMapping("/mutant")
    public ResponseEntity<Void> isMutant (@RequestBody Dna dna){
        if(Mutant.isMutant(dna.getDna())){
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
