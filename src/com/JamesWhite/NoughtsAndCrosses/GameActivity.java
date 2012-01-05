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
	LinearLayout cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
	int cellId, rowId;

	// Define the player choice buttons
	Button selectNoughts;
	Button selectCrosses;

	// Define the onClick parent and child views
	LinearLayout parent;
	TextView child;
	LinearLayout parentRow;
	TextView childTextView;
	LinearLayout childParent;

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

				human.setType(game.getNoughtValue());
				computer.setType(game.getCrossValue());

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

				EditText playerName = (EditText) dialog
						.findViewById(R.id.playerName);
				String playerNameString = playerName.getText().toString();
				human.setName(playerNameString);

				dialog.dismiss();

			}

		});

		// Instantiate all our Cells and Cell Values
		cell1 = (LinearLayout) findViewById(R.id.cell1);
		cell2 = (LinearLayout) findViewById(R.id.cell2);
		cell3 = (LinearLayout) findViewById(R.id.cell3);
		cell4 = (LinearLayout) findViewById(R.id.cell4);
		cell5 = (LinearLayout) findViewById(R.id.cell5);
		cell6 = (LinearLayout) findViewById(R.id.cell6);
		cell7 = (LinearLayout) findViewById(R.id.cell7);
		cell8 = (LinearLayout) findViewById(R.id.cell8);
		cell9 = (LinearLayout) findViewById(R.id.cell9);

		// Set up our cell click listeners
		cell1.setOnClickListener(this);
		cell2.setOnClickListener(this);
		cell3.setOnClickListener(this);
		cell4.setOnClickListener(this);
		cell5.setOnClickListener(this);
		cell6.setOnClickListener(this);
		cell7.setOnClickListener(this);
		cell8.setOnClickListener(this);
		cell9.setOnClickListener(this);

		// Let the games begin!
		game.setup();

	}

	@Override
	public void onClick(View v) {

		// Set the value for the clicked cell
		parent = (LinearLayout) findViewById(v.getId());
		child = (TextView) parent.getChildAt(0);

		// Get the Cell ID
		try {

			cellId = Integer.parseInt(child.getTag().toString());

		}

		catch (NumberFormatException nfe) {

			System.out.println("Could not parse " + nfe);

		}
		
		// Set up the toast notifications
		Toast toast;
		Context context = getApplicationContext();
		String text;
		int duration = Toast.LENGTH_SHORT;

		// Add the players move
		if (game.getStatus() == game.ACTIVE) {
			
			if (game.getGridValue(cellId) != 1 || game.getGridValue(cellId) != 2) {
				
				// +1 to the player type because we have to use 1/2
				// in the gridValue array because the default for an
				// int array is 0
				game.setGridValue(cellId, human.getType() + 1);
				child.setText(game.getStringFromPlayerType(human.getType()));
			
				// Update the game status
				game.setStatus(game.WAITING);
			
				// Show the nice move notification
				text = "Nice move " + human.getName() + ", now it's Skynet's turn!";
				toast = Toast.makeText(context, text, duration);
				toast.cancel(); // Cancel any already visible slices
				toast.show();
				
			}
			
			else {
				
				// Show the nice move notification
				text = "Looks like that position is already taken!";
				toast = Toast.makeText(context, text, duration);
				toast.cancel(); // Cancel any already visible slices
				toast.show();
				
			}
			
		}
			
		else {
			
			// Show the nice move notification
			text = "Wait your turn!";
			toast = Toast.makeText(context, text, duration);
			toast.cancel(); // Cancel any already visible slices
			toast.show();
			
		}

		// Let the CPU make it's move!
		computer.calculateNextMove(game.getGridValues());

	}

}