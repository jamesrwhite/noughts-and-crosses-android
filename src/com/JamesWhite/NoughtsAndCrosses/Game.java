package com.JamesWhite.NoughtsAndCrosses;

public class Game {

	private int score;
	private int status;
	public final int ACTIVE = 1;
	public final int WAITING = 2;
	public final int UNFINISHED = 3;
	public final int FINISHED = 4;
	private final int NOUGHT = -1;
	private final int CROSS = 1;
	// Make the array length 10 as opposed to 9 so we don't
	// have to used a 0 based index as it makes it really messy
	// having to use -1's everywhere, gridValues[0] will be ignored
	private int[] gridValues = new int[9];

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

		int patternPart1, patternPart2, patternPart3, sum;

		// The 8 possible winning patterns
		String[] winningPatterns = {
				"0,1,2",
				"3,4,5",
				"6,7,8",
				"0,3,6",
				"1,4,7",
				"2,5,8",
				"0,4,8",
				"2,4,6"
		};
		
		// Loop through the patterns and see if any match
		for (String pattern : winningPatterns) {
			
			String patternParts[] = pattern.split(",");
			
			patternPart1 = Integer.parseInt(patternParts[0]);
			patternPart2 = Integer.parseInt(patternParts[1]);
			patternPart3 = Integer.parseInt(patternParts[2]);
			
			sum = gridValues[patternPart1] + gridValues[patternPart2] + gridValues[patternPart3];
			
			if (sum == -3 || sum == 3) {
			
				return sum/3;
				
			}
			
		}

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
