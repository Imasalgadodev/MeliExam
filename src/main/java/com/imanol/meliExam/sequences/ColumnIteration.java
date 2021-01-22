package com.imanol.meliExam.sequences;

public class ColumnIteration implements SequenceIteration {

	private final char[][] matrix;
	private final int column;

	private int index;

	public ColumnIteration(char[][] matrix, int column) {
		this.matrix = matrix;
		this.column = column;
		this.index = 0;
	}

	@Override
	public char next() {
		return this.matrix[this.column][this.index++];
	}

	@Override
	public int remaining() {
		return this.matrix.length - this.index;
	}
}
