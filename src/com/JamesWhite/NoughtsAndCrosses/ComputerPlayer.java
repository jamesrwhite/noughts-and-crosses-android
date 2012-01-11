package com.JamesWhite.NoughtsAndCrosses;

import java.util.Arrays;
import java.util.Collections;

public class ComputerPlayer extends Player {

	int nextMove;

	public int getNextMove(int[] gridValues) {

		// 8 1 6
		// 3 5 7
		// 4 9 2

		String[] twoInARowPatterns = { "8,1", "1,6", "3,5", "5,7", "4,9",
				"9,2", "8,3", "3,4", "1,5", "5,9", "6,7", "7,2", "8,5", "5,2",
				"6,5", "5,4", "6,2", "8,6", "8,4", "6,4", "8,2", "9,1" };
		
		// Shuffle it!
		Collections.shuffle(Arrays.asList(twoInARowPatterns));

		for (String pattern : twoInARowPatterns) {

			String patternParts[] = pattern.split(",");

			int patternPart1 = Integer.parseInt(patternParts[0]);
			int patternPart2 = Integer.parseInt(patternParts[1]);

			int placeToGo = 15 - (patternPart1 + patternPart2);

			if (gridValues[patternPart1 - 1] == super.getType()
					&& gridValues[patternPart2 - 1] == super.getType()
					&& gridValues[placeToGo - 1] == 0) {
				
				System.out.println("hey");
				System.out.println(15 - (patternPart1 + patternPart2));

				return placeToGo;

			}

		}
		
		for (String pattern : twoInARowPatterns) {

			String patternParts[] = pattern.split(",");

			int patternPart1 = Integer.parseInt(patternParts[0]);
			int patternPart2 = Integer.parseInt(patternParts[1]);

			int placeToGo = 15 - (patternPart1 + patternPart2);

			if (gridValues[patternPart1 - 1] == (-1*super.getType())
					&& gridValues[patternPart2 - 1] == (-1*super.getType())
					&& gridValues[placeToGo - 1] == 0) {

				System.out.println(15 - (patternPart1 + patternPart2));

				return placeToGo;

			}

		}

		nextMove = (int) Math.round(Math.random() * 8);

		System.out.println("p" + nextMove);

		if (gridValues[nextMove] == 0) {

			System.out.println("FOUND A PLACE TO GO @ p" + nextMove);

		}

		else {

			this.getNextMove(gridValues);

		}

		return (nextMove + 1);

	}
}