package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

	// Create our players
	Player human = new Player();
	Player computer = new Player();

	// Create the game
	Game game = new Game();

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		// Set up the players
		human.setType(game.nought());
		computer.setType(game.cross());
		
		// Let the games begin!
		game.setup();

	}

}