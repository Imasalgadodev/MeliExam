package com.imanol.meliExam.sequences;

public class RowIteration implements SequenceIteration {

	private final char[][] matrix;
	private final int row;

	private int index;

	public RowIteration(char[][] matrix, int row) {
		this.matrix = matrix;
		this.row = row;
		this.index = 0;
	}

	@Override
	public char next() {
		return this.matrix[this.index++][this.row];
	}

	@Override
	public int remaining() {
		return this.matrix.length - this.index;
	}
}
