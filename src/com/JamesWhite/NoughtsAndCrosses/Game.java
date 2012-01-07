package com.JamesWhite.NoughtsAndCrosses;

public class Game {

	private int score;
	private int status;
	public final int ACTIVE = 1;
	public final int WAITING = 2;
	public final int UNFINISHED = 3;
	public final int FINISHED = 4;
	private final int NOUGHT = 1;
	private final int CROSS = 2;
	// Make the array length 10 as opposed to 9 so we don't
	// have to used a 0 based index as it makes it really messy
	// having to use -1's everywhere, gridValues[0] will be ignored
	private int[] gridValues = new int[10];

	/**
	 * @return the game score
	 */
	public int getScore() {

		return score;

	}

	/**
	 * @param score
	 *            set the game score
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
	 * @param status
	 *            set the game status with one of the status constants
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
	 * @return int game status Take the game grid and see if all the positions
	 *         are taken
	 */
	public int checkIfGridFull(int[] gridValues) {

		for (int i = 1; i < gridValues.length; i++) {

			if (gridValues[i] == 0) {

				return this.UNFINISHED;

			}

		}

		return this.FINISHED;

	}

	/**
	 * @param int[] gridValues
	 * @return int id of winning player or false
	 */
	public int checkIfGameWon(int[] gridValues) {

		int sum;

		// Check each possible combination (8) to see if they have won
		// winning combinations add up to 15 or 30
		// 15 being noughts and 30 being crosses

		// * * *
		// - - -
		// - - -
		sum = (gridValues[4] * 4) + (gridValues[3] * 3) + (gridValues[8] * 8);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// - - -
		// * * *
		// - - -
		sum = (gridValues[9] * 9) + (gridValues[5] * 5) + (gridValues[1] * 1);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// - - -
		// - - -
		// * * *
		sum = (gridValues[2] * 2) + (gridValues[7] * 7) + (gridValues[6] * 6);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// * - -
		// * - -
		// * - -
		sum = (gridValues[4] * 4) + (gridValues[9] * 9) + (gridValues[2] * 2);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// - * -
		// - * -
		// - * -
		sum = (gridValues[3] * 3) + (gridValues[5] * 5) + (gridValues[7] * 7);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// - - *
		// - - *
		// - - *
		sum = (gridValues[8] * 8) + (gridValues[1] * 1) + (gridValues[6] * 6);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// * - -
		// - * -
		// - - *
		sum = (gridValues[4] * 4) + (gridValues[5] * 5) + (gridValues[6] * 6);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// * - -
		// - * -
		// - - *
		sum = (gridValues[8] * 8) + (gridValues[5] * 5) + (gridValues[2] * 2);
		if (sum == 15)
			return this.NOUGHT;

		else if (sum == 30)
			return this.CROSS;

		// If nothing matches, then nobody has won..
		return 0;

	}

	/**
	 * @param gridValue
	 *            the gridValues to set from the gridValues Array.
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
