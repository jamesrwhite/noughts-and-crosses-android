package com.JamesWhite.NoughtsAndCrosses;

/**
 * 
 * ComputerPlayer
 * 
 * @author James White
 * 
 * Handles the core of the game's AI
 * 
 */

import java.util.Arrays;
import java.util.Collections;

import android.app.AlertDialog;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class ComputerPlayer extends Player {
	/**
	 * getNextMove Calculate the next move for the computer using a simple
	 * algorithim
	 * 
	 * @param gridValues
	 * @return nextMove
	 * @author James White
	 */
	public int getNextMove(int[] gridValues) {

		// 8 1 6
		// 3 5 7
		// 4 9 2

		// All the possible two in a row combitnations
		String[] twoInARowPatterns = { "8,1", "1,6", "3,5", "5,7", "4,9",
				"9,2", "8,3", "3,4", "1,5", "5,9", "6,7", "7,2", "8,5", "5,2",
				"6,5", "5,4", "6,2", "8,6", "8,4", "6,4", "8,2", "9,1", "2,4",
				"3,7" };

		// Shuffle it!
		Collections.shuffle(Arrays.asList(twoInARowPatterns));

		// First look if the game can be won by the CPU
		for (String pattern : twoInARowPatterns) {

			String patternParts[] = pattern.split(",");

			int patternPart1 = Integer.parseInt(patternParts[0]);
			int patternPart2 = Integer.parseInt(patternParts[1]);

			int placeToGo = 15 - (patternPart1 + patternPart2);

			if (gridValues[patternPart1 - 1] == super.getType()
					&& gridValues[patternPart2 - 1] == super.getType()
					&& gridValues[placeToGo - 1] == 0) {

				return placeToGo;

			}

		}

		// Then look to see if we need to defend against any possible winning
		// moves
		for (String pattern : twoInARowPatterns) {

			String patternParts[] = pattern.split(",");

			int patternPart1 = Integer.parseInt(patternParts[0]);
			int patternPart2 = Integer.parseInt(patternParts[1]);

			int placeToGo = 15 - (patternPart1 + patternPart2);

			if (gridValues[patternPart1 - 1] == (-1 * super.getType())
					&& gridValues[patternPart2 - 1] == (-1 * super.getType())
					&& gridValues[placeToGo - 1] == 0) {

				return placeToGo;

			}

		}

		// If we can't block a winning move or make one of our own at least go
		// somewhere that's vaguely useful! i.e somewhere that could lead to a
		// win
		// String[] surroundingPositions = { "8,6,5", "7,5,9", "8,5,4", "3,5,9",
		// "8,1,6,7,2,9,4", "1,5,7", "6,5,2", "1,5,3", "4,5,2" };
		//
		// for (int i = 0; i < gridValues.length; i++) {
		//
		// if (gridValues[i] == super.getType()) {
		//
		// String surroundingPosition[] = surroundingPositions[i]
		// .split(",");
		//
		// // Shuffle it!
		// Collections.shuffle(Arrays.asList(surroundingPosition));
		//
		// for (String position : surroundingPosition) {
		//
		// int positionInt = Integer.parseInt(position);
		//
		// if (gridValues[positionInt - 1] == 0) {
		//
		// return positionInt;
		//
		// }
		//
		// }
		//
		// }
		//
		// }

		// If neither of the above are relevant, go somewhere random
		int nextMove;

		do {

			nextMove = (int) Math.round(Math.random() * 8);

		}

		while (gridValues[nextMove] != 0);

		return (nextMove + 1);

	}
}