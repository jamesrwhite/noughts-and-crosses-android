package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements OnClickListener {

	// Define all the grid cells
	LinearLayout cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8;

	// Define the player choice buttons
	Button selectNoughts;
	Button selectCrosses;

	// Define the onClick parent and child views
	LinearLayout parent;
	TextView child;

	// Create our players
	HumanPlayer human = new HumanPlayer();
	ComputerPlayer computer = new ComputerPlayer();

	// Create the game
	Game game = new Game();

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.game);

		// Show our dialog to get the player type choice
		final Dialog dialog = new Dialog(GameActivity.this);
		dialog.setContentView(R.layout.gamestartdiaglog);
		dialog.setTitle("Choose wisely!");
		dialog.show();

		// Instantiate our player choice buttons and set their click listeners
		selectNoughts = (Button) dialog.findViewById(R.id.selectNoughts);
		selectNoughts.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				human.setType(game.getCrossValue());
				computer.setType(game.getNoughtValue());
				
				EditText playerName = (EditText) dialog.findViewById(R.id.playerName);
				String playerNameString = playerName.getText().toString();
				human.setName(playerNameString);
				
				dialog.dismiss();

			}

		});

		selectCrosses = (Button) dialog.findViewById(R.id.selectCrosses);
		selectCrosses.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				human.setType(game.getCrossValue());
				computer.setType(game.getNoughtValue());
				
				EditText playerName = (EditText) dialog.findViewById(R.id.playerName);
				String playerNameString = playerName.getText().toString();
				human.setName(playerNameString);
				
				dialog.dismiss();

			}

		});

		// Instantiate all our Cells and Cell Values
		cell0 = (LinearLayout) findViewById(R.id.cell0);
		cell1 = (LinearLayout) findViewById(R.id.cell1);
		cell2 = (LinearLayout) findViewById(R.id.cell2);
		cell3 = (LinearLayout) findViewById(R.id.cell3);
		cell4 = (LinearLayout) findViewById(R.id.cell4);
		cell5 = (LinearLayout) findViewById(R.id.cell5);
		cell6 = (LinearLayout) findViewById(R.id.cell6);
		cell7 = (LinearLayout) findViewById(R.id.cell7);
		cell8 = (LinearLayout) findViewById(R.id.cell8);

		// Set up our cell click listeners
		cell0.setOnClickListener(this);
		cell1.setOnClickListener(this);
		cell2.setOnClickListener(this);
		cell3.setOnClickListener(this);
		cell4.setOnClickListener(this);
		cell5.setOnClickListener(this);
		cell6.setOnClickListener(this);
		cell7.setOnClickListener(this);
		cell8.setOnClickListener(this);

		// Let the games begin!
		game.setup();

	}

	@Override
	public void onClick(View v) {

		parent = (LinearLayout) findViewById(v.getId());
		child = (TextView) parent.getChildAt(0);
		
		// Get the String of nought/cross to set based on the integer player type
		child.setText(game.getStringFromPlayerType(human.getType()));
		
		// Display a notification that it is now the CPU's go
		Context context = getApplicationContext();
		String text = "Nice move " + human.getName() + ", now it's Skynet's turn!";
		int duration = 1;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		computer.calculateNextMove(game.getGridValues());

	}

}