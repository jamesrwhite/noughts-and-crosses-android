package com.JamesWhite.NoughtsAndCrosses;

/*
 * The Grid is a 3x3 square with
 * a number assigned to each position
 * which corresponds to it's array key
 * 
 * ------------
 *  0   1   2
 *  3   4   5
 *  6   7   8
 * ------------
 * 
 */

public class GameGrid {
	
	private int[] gridValues = new int[9];

	/**
	 * @return the gridValues
	 */
	public int[] getGridValues() {
		
		return gridValues;
		
	}

	/**
	 * @param gridValue the gridValues to set from the gridValues Array
	 */
	public void setGridValue(int gridIndex, int gridValue) {
		
		gridValues[gridIndex] = gridValue;
		
	}
	
	/**
	 * Reset the grid
	 */
	public void resetGrid() {
		
		// Empty array or set all values to NULL
		
	}
	
}
