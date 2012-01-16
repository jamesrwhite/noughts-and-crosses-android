package com.JamesWhite.NoughtsAndCrosses;

/**
 * 
 * Game
 * 
 * Handles access to the games key attributes and some helpful functions
 * 
 * @author James White
 * 
 */

import java.util.Arrays;
import java.util.Collections;

public class Game {

	public final int ACTIVE = 1;
	public final int FINISHED = 0;

	private int score = 0;
	private int startTime;
	private int time;
	private int status;
	private int currentTurn = 0; // Whose go is it? Based on what type they are
	private int winner = 0;
	private int moves = 0;
	private final int NOUGHT = -1;
	private final int CROSS = 1;
	private int[] gridValues = new int[9];

	/**
	 * getScore
	 * 
	 * @return the game score
	 */
	public int getScore() {

		return score;

	}

	/**
	 * Update the current score, lower is better
	 */
	public void updateScore() {

		this.updateTime();

		int score = this.score
				+ ((this.getTime() - this.startTime) * this.getMoves());

		this.setScore(score);

	}

	/**
	 * setScore set the game score
	 * 
	 * @param score
	 */
	public void setScore(int score) {

		this.score = score;

	}

	/**
	 * getTime
	 * 
	 * @return the time
	 */
	public int getTime() {

		return time;

	}

	/**
	 * setTime the time to set
	 * 
	 * @param time
	 */
	public void setTime(int time) {

		this.time = time;

	}

	/**
	 * updateTime
	 * 
	 * Update the time based on the current unix timestamp
	 */
	public void updateTime() {

		this.setTime((int) (System.currentTimeMillis() / 1000L));

	}

	/**
	 * getCurrentTurn
	 * 
	 * @return the currentTurn
	 */
	public int getCurrentTurn() {

		return currentTurn;

	}

	/**
	 * setStartTime set the start time of the game
	 * 
	 * @param time
	 */
	public void setStartTime(int startTime) {

		this.startTime = startTime;

	}

	/**
	 * setCurrentTurn the currentTurn to set, +- 1
	 * 
	 * @param currentTurn
	 */
	public void setCurrentTurn(int currentTurn) {

		this.currentTurn = currentTurn;

	}

	/**
	 * getWinner
	 * 
	 * @return the winner
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * setWinner
	 * 
	 * @param winner
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * getMoves get the amount of moves made
	 * 
	 * @return the moves
	 */
	public int getMoves() {

		return moves;

	}

	/**
	 * setMoves set the number of moves that have been made
	 * 
	 * @param moves
	 */
	public void setMoves(int moves) {

		this.moves = moves;

	}

	/**
	 * getStatus
	 * 
	 * @return the game status
	 */
	public int getStatus() {

		return status;

	}

	/**
	 * setStatus set the game status with one of the status constants
	 * 
	 * @param status
	 */
	public void setStatus(int status) {

		this.status = status;

	}

	/**
	 * getNoughtValuethe integer value of a 'nought
	 * 
	 * @return -1
	 */
	public int getNoughtValue() {

		return this.NOUGHT;

	}

	/**
	 * getCrossValue the integer value of a 'cross'
	 * 
	 * @return 1
	 */
	public int getCrossValue() {

		return this.CROSS;

	}

	/**
	 * getStringFromPlayerType
	 * 
	 * @return String value of the player type given as a parameter
	 * @param int player type Used for setting the text in the XML layout files
	 */
	public String getStringFromPlayerType(int type) {

		if (type == this.CROSS) {

			return "X";

		}

		else if (type == this.NOUGHT) {

			return "O"; // Use O instad of 0 as it looks better with the font we
						// are using

		}

		return null;

	}

	/**
	 * getGridValues
	 * 
	 * @return gridValues
	 */
	public int[] getGridValues() {

		return gridValues;

	}

	/**
	 * getGridValue
	 * 
	 * @return an individual gridValues
	 */
	public int getGridValue(int index) {

		return gridValues[index];

	}

	/**
	 * checkIfGridFull Take the game grid and see if all the positions are taken
	 * 
	 * @param gridValues
	 * @return game status
	 */
	public int checkIfGridFull() {

		for (int i = 1; i < gridValues.length; i++) {

			if (gridValues[i] == 0) {

				return this.ACTIVE;

			}

		}

		return this.FINISHED;

	}

	/**
	 * checkIfGameWon check against the winning patterns to see if the game has
	 * been won
	 * 
	 * @param int[] gridValues
	 * @return int id of winning player or false
	 */
	public int checkIfGameWon() {

		int patternPart1, patternPart2, patternPart3, sum;

		// The 8 possible winning patterns
		String[] winningPatterns = { "8,1,6", "3,5,7", "4,9,2", "8,3,4",
				"1,5,9", "6,7,2", "8,5,2", "6,5,4" };

		// Shuffle it!
		Collections.shuffle(Arrays.asList(winningPatterns));

		// Loop through the patterns and see if any match
		for (String pattern : winningPatterns) {

			String patternParts[] = pattern.split(",");

			patternPart1 = Integer.parseInt(patternParts[0]);
			patternPart2 = Integer.parseInt(patternParts[1]);
			patternPart3 = Integer.parseInt(patternParts[2]);

			sum = gridValues[patternPart1 - 1] + gridValues[patternPart2 - 1]
					+ gridValues[patternPart3 - 1];

			// If the Sum of the 3 grid cells is +-3 then they have won
			// return +- to say which type has won, 1 being crosses and 0
			// noughts
			if (sum == -3 || sum == 3) {

				return (int) sum / 3;

			}

		}

		// If nothing matches, then nobody has won..
		return 0;

	}

	/**
	 * setGridValue
	 * 
	 * @param gridValue
	 *            the gridValues to set from the gridValues Array.
	 */
	public void setGridValue(int gridIndex, int gridValue) {

		gridValues[gridIndex] = gridValue;

	}

	/**
	 * resetGrid Reset the grid by setting all values to 0
	 */
	public void resetGrid() {

		for (int i = 0; i < this.getGridValues().length; i++) {

			this.setGridValue(i, 0);

		}

	}

	/**
	 * Do everything required to start the game
	 */
	public void start() {

		this.resetGrid();
		this.updateTime();
		this.setStartTime(this.getTime());
		this.setScore(0);
		this.setStatus(ACTIVE);

	}
}
