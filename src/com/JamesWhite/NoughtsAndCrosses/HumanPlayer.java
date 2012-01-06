package com.JamesWhite.NoughtsAndCrosses;

public class HumanPlayer extends Player {
	
	public void move(Game game, int gridPosition) {
		
		// Update the grid values array
		// with the new position the player
		// wishes to move to
		
		game.setGridValue(gridPosition, this.getType());
		
	}

}
