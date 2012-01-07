package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements OnClickListener {

	// Define all the grid cells
	private LinearLayout cell1, cell2, cell3, cell4, cell5, cell6, cell7,
			cell8, cell9;
	int cellId, rowId;

	// Define the player choice buttons
	private Button selectNoughts, selectCrosses;

	// Define the onClick parent and child views
	private LinearLayout parent;
	private TextView child;
	private LinearLayout[] cells;

	// Create our players
	private HumanPlayer human = new HumanPlayer();
	private ComputerPlayer computer = new ComputerPlayer();

	// Create the game
	private Game game = new Game();

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

				EditText playerName = (EditText) dialog
						.findViewById(R.id.playerName);
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

		// Instantiate all our Cells
		cell1 = (LinearLayout) findViewById(R.id.cell1);
		cell2 = (LinearLayout) findViewById(R.id.cell2);
		cell3 = (LinearLayout) findViewById(R.id.cell3);
		cell4 = (LinearLayout) findViewById(R.id.cell4);
		cell5 = (LinearLayout) findViewById(R.id.cell5);
		cell6 = (LinearLayout) findViewById(R.id.cell6);
		cell7 = (LinearLayout) findViewById(R.id.cell7);
		cell8 = (LinearLayout) findViewById(R.id.cell8);
		cell9 = (LinearLayout) findViewById(R.id.cell9);

		// Add all the grid cells to an Array
		cells = new LinearLayout[9];
		int arrayIndex;
		LinearLayout row1 = (LinearLayout) findViewById(R.id.row1);
		LinearLayout row2 = (LinearLayout) findViewById(R.id.row2);
		LinearLayout row3 = (LinearLayout) findViewById(R.id.row3);

		// Populate the cells array with each LinearLayout grid cell
		for (int i = 0; i < 9; i++) {

			// Row 1
			if (i >= 0 && i <= 2) {

				// arrayIndex = Integer.parseInt(((ViewGroup) row1.getChildAt(i
				// - 1)).getChildAt(0).getTag().toString());
				cells[i] = (LinearLayout) row1.getChildAt(i);

			}

			// Row 2
			if (i >= 3 && i <= 5) {

				// arrayIndex = Integer.parseInt(((ViewGroup) row2.getChildAt(i
				// - 1)).getChildAt(0).getTag().toString());
				cells[i] = (LinearLayout) row2.getChildAt(i - 3);

			}

			// Row 3
			if (i >= 6 && i <= 8) {

				// arrayIndex = Integer.parseInt(((ViewGroup) row3.getChildAt(i
				// - 1)).getChildAt(0).getTag().toString());
				cells[i] = (LinearLayout) row3.getChildAt(i - 6);

			}

		}
		
		for (LinearLayout i : cells) {
			
			System.out.println(i);
			
		}

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
		cellId = Integer.parseInt(child.getTag().toString());

		// Set up the toast notifications
		Toast toast;
		Context context = getApplicationContext();
		String text;
		int duration = Toast.LENGTH_SHORT;

		// Add the players move
		// Check the grid isn't full
		if (game.checkIfGridFull(game.getGridValues()) == game.UNFINISHED) {

			if (game.getStatus() == game.ACTIVE) {

				if (game.getGridValue(cellId) == 0) {

					System.out.println("Player 1 to p" + (cellId));
					game.setGridValue(cellId, human.getType());
					child.setText(game.getStringFromPlayerType(human.getType()));

					// Check if this moves wins the game
					if (game.checkIfGameWon(game.getGridValues()) != 0) {

						// Show the game over notification
						text = "#winning";
						toast = Toast.makeText(context, text, duration);
						toast.cancel(); // Cancel any already visible slices
						toast.show();
						game.setStatus(game.FINISHED);

					}

					// Otherwise let the games continue!
					else {

						game.setStatus(game.WAITING);

					}

					// Show the nice move notification
					// text = "Nice move " + human.getName() +
					// ", now it's Skynet's turn!";
					// toast = Toast.makeText(context, text, duration);
					// toast.cancel(); // Cancel any already visible slices
					// toast.show();

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

			// Check AGAIN if the game is finished
			if (game.getStatus() != game.FINISHED
					&& game.checkIfGridFull(game.getGridValues()) == game.UNFINISHED) {

				// Let the CPU make it's move!
				int cpuNextMove = computer.getNextMove(game.getGridValues());
				System.out.println("CPU to p" + (cpuNextMove));
				game.setGridValue(cpuNextMove, computer.getType());
				TextView cpuTextView = (TextView) cells[cpuNextMove]
						.getChildAt(0);
				cpuTextView.setText(game.getStringFromPlayerType(computer
						.getType()));

				if (game.checkIfGameWon(game.getGridValues()) != 0) {

					// Show the game over notification
					text = "You Looooose!";
					toast = Toast.makeText(context, text, duration);
					toast.cancel(); // Cancel any already visible slices
					toast.show();
					game.setStatus(game.FINISHED);

				}

				else {

					game.setStatus(game.ACTIVE);

				}

			}

			else {

				// Show the game over notification
				text = "Game over suckaz";
				toast = Toast.makeText(context, text, duration);
				toast.cancel(); // Cancel any already visible slices
				toast.show();

			}

		}

		else {

			// Show the game over notification
			text = "Game over suckaz";
			toast = Toast.makeText(context, text, duration);
			toast.cancel(); // Cancel any already visible slices
			toast.show();

		}

	}

}