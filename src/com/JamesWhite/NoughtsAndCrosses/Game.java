package com.JamesWhite.NoughtsAndCrosses;

public class Game {
	
	private int score;
	private int status;
		public final int ACTIVE = 1;
		public final int WAITING = 2;
		public final int UNFINISHED = 3;
		public final int FINISHED = 0;
	private final int NOUGHT = 0;
	private final int CROSS = 1;
	private int[] gridValues = new int[9];

	/**
	 * @return the game score
	 */
	public int getScore() {
		
		return score;
		
	}

	/**
	 * @param score set the game score
	 */
	public void setScore(int score) {
		
		this.score = score;
		
	}
	
	/**
	 * @return the game status
	 */
	public int getStatus() {
		
		return status;
		
	}
	
	/**
	 * @param status set the game status with one of
	 * the status constants
	 */
	public void setStatus(int status) {
		
		this.status = status;
		
	}
	
	/**
	 * @return the integer value of a 'nought'
	 */
	public int getNoughtValue() {
		
		return this.NOUGHT;
		
	}
	
	/**	
	 * @return the integer value of a 'cross'
	 */
	public int getCrossValue() {
		
		return this.CROSS;
		
	}
	
	/**
	 * @return String value of the player type given as a parameter
	 * @param int player type
	 */
	public String getStringFromPlayerType(int type) {
		
		if (type == this.CROSS) {
			
			return "X";
			
		}
		
		else if (type == this.NOUGHT) {
			
			return "0";
			
		}
		
		else {
			
			return null;
			
		}
		
	}
	
	/**
	 * @return the gridValues
	 */
	public int[] getGridValues() {
		
		return gridValues;
		
	}
	
	/**
	 * @return the gridValues
	 */
	public int getGridValue(int index) {
		
		return gridValues[index];
		
	}
	
	/**
	 * @param int[] gridValues 
	 * @return game status
	 * Take the game grid and see if all the positions are taken
	 */
	public int checkIfGameFinished(int[] gridValues) {
		
		int empty = 0;
		
		for (int i = 0; i < gridValues.length; i++) {
			
			if (gridValues[i] == 0) {
				
				return this.FINISHED;
				
			}
			
		}
		
		return this.UNFINISHED;
		
	}

	/**
	 * @param gridValue the gridValues to set from the gridValues Array.
	 */
	public void setGridValue(int gridIndex, int gridValue) {
		
		gridValues[gridIndex] = gridValue;
		
	}
	
	/**
	 * Reset the grid
	 */
	public void resetGrid() {
		
		// this.gridValues = null;
		
	}
	
	/**
	 * Do everything required to start the game
	 */
	public void setup() {
		
		this.resetGrid();
		this.setScore(0);
		this.setStatus(ACTIVE);
		
	}
	
}
