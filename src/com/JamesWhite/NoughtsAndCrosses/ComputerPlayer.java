package com.JamesWhite.NoughtsAndCrosses;

public class ComputerPlayer extends Player {
	
	int nextMove;
	
	public int getNextMove(int[] gridValues) {
		
		nextMove = (int) Math.round(Math.random() * 9);
			
		if (gridValues[nextMove] == 0 && gridValues[nextMove] != 1 && gridValues[nextMove] != 2 && nextMove != 0) {
			
			//System.out.println("FOUND A PLACE TO GO");
			
		}
			
		else {
				
			//System.out.println("RECURSIONZ");
			this.getNextMove(gridValues);
				
		}
		
		return nextMove;
		
	}

}