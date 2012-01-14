package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.util.Log;
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
	private int cellId;

	// Define the player choice buttons
	private Button selectNoughts, selectCrosses;

	// Define the onClick parent and child views
	private LinearLayout parent;
	private TextView child;
	private LinearLayout[] cells;
	
	// Define our external font used in the game
	Typeface alphaMack;
	
	// Define our TextView used to show the score
	private TextView score;

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

				// Get the players name and type choice
				human.setType(game.getNoughtValue());
				computer.setType(game.getCrossValue());

				game.setCurrentTurn(game.getNoughtValue());

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

				// Get the players name and type choice
				human.setType(game.getCrossValue());
				computer.setType(game.getNoughtValue());

				game.setCurrentTurn(game.getCrossValue());

				EditText playerName = (EditText) dialog
						.findViewById(R.id.playerName);
				String playerNameString = playerName.getText().toString();
				human.setName(playerNameString);

				dialog.dismiss();

			}

		});

		// Add all the grid cells to an Array
		cells = new LinearLayout[9];
		int arrayIndex;
		LinearLayout childLinear;
		TextView childText;
		LinearLayout row1 = (LinearLayout) findViewById(R.id.row1);
		LinearLayout row2 = (LinearLayout) findViewById(R.id.row2);
		LinearLayout row3 = (LinearLayout) findViewById(R.id.row3);

		// Populate the cells array with each LinearLayout grid cell
		for (int i = 0; i < row1.getChildCount(); i++) {

			childLinear = (LinearLayout) row1.getChildAt(i);
			childText = (TextView) childLinear.getChildAt(0);
			arrayIndex = Integer.parseInt((String) childText.getTag());

			cells[arrayIndex - 1] = childLinear;

		}

		for (int i = 0; i < row2.getChildCount(); i++) {

			childLinear = (LinearLayout) row2.getChildAt(i);
			childText = (TextView) childLinear.getChildAt(0);
			arrayIndex = Integer.parseInt((String) childText.getTag());

			cells[arrayIndex - 1] = childLinear;

		}

		for (int i = 0; i < row3.getChildCount(); i++) {

			childLinear = (LinearLayout) row3.getChildAt(i);
			childText = (TextView) childLinear.getChildAt(0);
			arrayIndex = Integer.parseInt((String) childText.getTag());

			cells[arrayIndex - 1] = childLinear;

		}

		// Set the onClickListeners for each of the cells and the font for the TextView's
		alphaMack = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/alphamack.ttf");
		TextView textView;
		
		for (LinearLayout cell : cells) {

			cell.setOnClickListener(this);
			
			textView = (TextView) cell.getChildAt(0);
			textView.setTypeface(alphaMack);

		}
		
		// Create out text view to show the score
		score = (TextView) findViewById(R.id.highScoreValue);

		// Let the games begin!
		game.setup();

	}

	@Override
	public void onClick(View v) {

		// Set the value for the clicked cell
		parent = (LinearLayout) findViewById(v.getId());
		child = (TextView) parent.getChildAt(0);
		cellId = Integer.parseInt(child.getTag().toString());

		// To be sure people are spam tapping the keyboard, temporarily
		// remove the click listeners
		removeClickListeners();

		// Set up the Alert Dialog to be used in the game logic
		final AlertDialog.Builder builder = new AlertDialog.Builder(this)
				.setCancelable(true)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								// Stop the current activity
								finish();

								Intent gameIntent = new Intent(
										GameActivity.this, GameActivity.class);
								gameIntent
										.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								GameActivity.this.startActivity(gameIntent);

							}
						})
				.setNegativeButton("Back to Menu",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								// Stop the current activity
								finish();

								Intent menuIntent = new Intent(
										GameActivity.this, MenuActivity.class);
								menuIntent
										.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								GameActivity.this.startActivity(menuIntent);

							}
						});

		// Set up the toast notifications
		Toast toast;
		Context context = getApplicationContext();
		String text;
		int duration = Toast.LENGTH_SHORT;

		//
		// Human game logic
		//

		// Check the game is active and not finished
		if (game.getStatus() == game.ACTIVE) {

			if (game.getCurrentTurn() == human.getType()) {

				if (game.getGridValue(cellId - 1) == 0) {

					// Update the TextView and gridValues Array
					game.setGridValue((cellId - 1), human.getType());
					child.setText(game.getStringFromPlayerType(human.getType()));
					
					// +1 to the games moves
					game.setMoves(game.getMoves() + 1);
					
					// Update the game score
					game.updateScore();
					score.setText("" + game.getScore() + "");

					// Check if the move just made wins the game
					if (game.checkIfGameWon() != 0) {

						builder.setMessage("You Win! Do you want to play again?");
						AlertDialog alert = builder.create();
						alert.show();
						
						LocalDatabase localDb = new LocalDatabase(context, "", null, 0);
						
						localDb.insertScore(human.getName(), game.getScore(), game.getTime());
						
						Cursor scores = localDb.getScores();
						System.out.println(scores);

						// Update the game status
						game.setStatus(game.FINISHED);
						game.setWinner(human.getType());

					}

					// Otherwise update the status and let the game continue
					else {

						// Update the current turn
						game.setCurrentTurn(computer.getType());

					}

				}

				// Inform the player this position has already been taken
				else {

					text = "Looks like that position is already taken!";
					toast = Toast.makeText(context, text, duration);
					toast.cancel(); // Cancel any already visible slices
					toast.show();
					
					// They failed, so let them have another pick..
					reAddClickListeners();

				}

			}

		}

		// Check again that the game is active!
		if (game.getStatus() == game.ACTIVE) {

			if (game.getCurrentTurn() == computer.getType()) {

				//
				// CPU Game Logic
				//

				// Check if the grid is full first before we begin
				if (game.checkIfGridFull() == game.FINISHED
						&& game.getWinner() == 0) {

					builder.setMessage("Draw! Do you want to play again?");
					AlertDialog alert = builder.create();
					alert.show();

					// Update the game status
					game.setStatus(game.FINISHED);

				}

				if (game.getStatus() != game.FINISHED) {

					text = "CPU is thinking..";
					toast = Toast.makeText(context, text, duration);
					toast.cancel(); // Cancel any already visible slices
					toast.show();
					
					// +1 to the games moves
					game.setMoves(game.getMoves() + 1);

					// Wait two seconds before showing the move to the player
					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {

						public void run() {
							
							// Update the game score
							game.updateScore();
							score.setText("" + game.getScore() + "");

							int cpuNextMove = computer.getNextMove(game
									.getGridValues());
							TextView cpuTextView = (TextView) cells[cpuNextMove - 1]
									.getChildAt(0);
							cpuTextView.setText(game
									.getStringFromPlayerType(computer.getType()));
							game.setGridValue((cpuNextMove - 1),
									computer.getType());

							// Check if the CPU's last move won it the game
							if (game.checkIfGameWon() == computer.getType()) {

								builder.setMessage("You Lost! Do you want to play again?");
								AlertDialog alert = builder.create();
								alert.show();

								// Update the game status
								game.setStatus(game.FINISHED);
								game.setWinner(computer.getType());

							}

							// Otherwise carry on
							else {

								game.setStatus(game.ACTIVE);
								game.setCurrentTurn(human.getType());

								// Now we can add in the click listeners again
								reAddClickListeners();
								
							}

						}

					}, 2500);

				}

			}

		}

	}

	public void removeClickListeners() {

		for (LinearLayout cell : cells) {

			cell.setOnClickListener(null);

		}

	}

	public void reAddClickListeners() {

		for (LinearLayout cell : cells) {

			cell.setOnClickListener(this);

		}

	}

}