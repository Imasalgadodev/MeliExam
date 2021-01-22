package com.imanol.meliExam.utils;


import com.imanol.meliExam.sequences.ColumnIteration;
import com.imanol.meliExam.sequences.DiagonalIteration;
import com.imanol.meliExam.sequences.RowIteration;
import com.imanol.meliExam.sequences.SequenceIteration;

public class Mutant {

	public static final int THRESHOLD = 4;

	public static boolean isMutant (String[] dna){
		char[][] dnaMatrix = convertToMatrix(dna);
		return isMatrixMutant(dnaMatrix);
	}

	private static char[][] convertToMatrix (String[] dna){
		char dnaMatrix[][] = new char[dna.length][];
		for (int i=0; i<dnaMatrix.length; i++)
			dnaMatrix[i] = dna[i].toCharArray();
		return dnaMatrix;
	}

	private static boolean isSequenceMutant(SequenceIteration sequence) {
		Character current;
		Character last = null;
		int count = 0;
		while (sequence.remaining() > 0) {
			current = sequence.next();
			if(current == last) {
				count++;
				if(count >= THRESHOLD) {
					return true;
				}
			} else {
				count = 1;
				last = current;
			}

			if(count + sequence.remaining() < THRESHOLD) {
				// Ya no puede ser
				return false;
			}
		}
		return false;
	}

	private static boolean isMatrixMutant(char[][] matrix) {
		int mutantSequences = 0;
		SequenceIteration iteration;

		for(int column = 0; column < matrix.length; column++) {
			iteration = new ColumnIteration(matrix, column);
			if(isSequenceMutant(iteration)) {
				mutantSequences++;
				if(mutantSequences > 1) {
					return true;
				}
			}
		}

		for(int row = 0; row < matrix.length; row++) {
			iteration = new RowIteration(matrix, row);
			if(isSequenceMutant(iteration)) {
				mutantSequences++;
				if(mutantSequences > 1) {
					return true;
				}
			}
		}

		iteration = new DiagonalIteration(matrix, true, true, 0, 0);
		if(isSequenceMutant(iteration)) {
			mutantSequences++;
			if(mutantSequences > 1) {
				return true;
			}
		}

		for (int i = 1; matrix.length - i > 2; i++) {
			iteration = new DiagonalIteration(matrix, true, true, 0, i);
			if(isSequenceMutant(iteration)) {
				mutantSequences++;
				if(mutantSequences > 1) {
					return true;
				}
			}
			iteration = new DiagonalIteration(matrix, true, true, i, 0);
			if(isSequenceMutant(iteration)) {
				mutantSequences++;
				if(mutantSequences > 1) {
					return true;
				}
			}
		}

		iteration = new DiagonalIteration(matrix, true, false, 0, matrix.length - 1);
		if(isSequenceMutant(iteration)) {
			mutantSequences++;
			if(mutantSequences > 1) {
				return true;
			}
		}

		for (int i = 1; matrix.length - i > 3; i++) {
			iteration = new DiagonalIteration(matrix, true, false, i, matrix.length - 1);
			if(isSequenceMutant(iteration)) {
				mutantSequences++;
				if(mutantSequences > 1) {
					return true;
				}
			}
			iteration = new DiagonalIteration(matrix, true, false, 0, matrix.length - 1 - i);
			if(isSequenceMutant(iteration)) {
				mutantSequences++;
				if(mutantSequences > 1) {
					return true;
				}
			}
		}

		return false;
	}
}
