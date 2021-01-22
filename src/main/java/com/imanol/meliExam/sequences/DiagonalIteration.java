package com.imanol.meliExam.sequences;

public class DiagonalIteration implements SequenceIteration {

	private final char[][] matrix;

	private final boolean xPositive;
	private final boolean yPositive;

	private int x;
	private int y;

	public DiagonalIteration(char[][] matrix, boolean xPositive, boolean yPositive, int x, int y) {
		this.matrix = matrix;
		this.xPositive = xPositive;
		this.yPositive = yPositive;
		this.x = x;
		this.y = y;
	}

	@Override
	public char next() {
		char current = this.matrix[this.x][this.y];

		this.x += xPositive ? 1 : -1;
		this.y += yPositive ? 1 : -1;

		return current;
	}


	public int getLimit(boolean positive, int pos) {
		if(positive) {
			return this.matrix.length - pos;
		} else {
			return pos + 1;
		}
	}

	@Override
	public int remaining() {
		int xRemaining = getLimit(xPositive,  x);
		int yRemaining = getLimit(yPositive,  y);
		return Math.min(xRemaining, yRemaining);
	}

}