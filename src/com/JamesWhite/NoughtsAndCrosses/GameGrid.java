package com.JamesWhite.NoughtsAndCrosses;

/*
 * The Grid is a 3x3 square with
 * a number assigned to each position
 * which corresponds to it's array key + 1
 * 
 * ------------
 *  1   2   3
 *  4   5   6
 *  7   8   9
 * ------------
 * 
 */

public class GameGrid {
	
	private int[] gridValues = new int[9];
	// Constants to represent the noughts and crosses
	private final int NOUGHT = 0;
	private final int CROSS = 1;

	/**
	 * @return the gridValues
	 */
	public int[] getGridValues() {
		
		return gridValues;
		
	}

	/**
	 * @param gridValue the gridValues to set from the gridValues Array.
	 * Setting gridIndex 1 will set the array index of 0's value.
	 */
	public void setGridValue(int gridIndex, int gridValue) {
		
		gridValues[gridIndex - 1] = gridValue;
		
	}
	
	/**
	 * Reset the grid
	 */
	public void resetGrid() {
		
		// Empty array or set all values to NULL
		
	}
	
	/**
	 * Return the integer value of a 'nought'
	 */
	public int getNought() {
		
		return this.NOUGHT;
		
	}
	
	/**
	 * Return the integer value of a 'cross'
	 */
	public int getCross() {
		
		return this.CROSS;
		
	}
	
}
