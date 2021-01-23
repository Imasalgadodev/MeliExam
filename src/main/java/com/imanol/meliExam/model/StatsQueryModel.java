package com.imanol.meliExam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatsQueryModel {

    private final boolean mutant;

    private final int count;

}
