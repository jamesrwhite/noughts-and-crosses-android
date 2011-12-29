package com.JamesWhite.NoughtsAndCrosses;

public class Game {
	
	private int score;
	private int status;
		private final int ACTIVE = 1;
		private final int WAITING = 2;
		private final int FINISHED = 0;

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
	 * Do everything required to start the game
	 */
	public void setup() {
		
		this.setScore(0);
		this.setStatus(ACTIVE);
		
	}
	
}
