package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity implements OnClickListener {
	
	// Define all the grid cells and text views
	LinearLayout cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8;
	TextView cell0Value, cell1Value, cell2Value, cell3Value, cell4Value,
	cell5Value, cell6Value, cell7Value, cell8Value;
	
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
		
		// Instantiate all our Cells and Cell Values
		cell0 = (LinearLayout) findViewById(R.id.cell0);
		cell0Value = (TextView) findViewById(R.id.cell0Value);
		cell1 = (LinearLayout) findViewById(R.id.cell1);
		cell1Value = (TextView) findViewById(R.id.cell1Value);
		cell2 = (LinearLayout) findViewById(R.id.cell2);
		cell2Value = (TextView) findViewById(R.id.cell2Value);
		cell3 = (LinearLayout) findViewById(R.id.cell3);
		cell3Value = (TextView) findViewById(R.id.cell3Value);
		cell4 = (LinearLayout) findViewById(R.id.cell4);
		cell4Value = (TextView) findViewById(R.id.cell4Value);
		cell5 = (LinearLayout) findViewById(R.id.cell5);
		cell5Value = (TextView) findViewById(R.id.cell5Value);
		cell6 = (LinearLayout) findViewById(R.id.cell6);
		cell6Value = (TextView) findViewById(R.id.cell6Value);
		cell7 = (LinearLayout) findViewById(R.id.cell7);
		cell7Value = (TextView) findViewById(R.id.cell7Value);
		cell8 = (LinearLayout) findViewById(R.id.cell8);
		cell8Value = (TextView) findViewById(R.id.cell8Value);
		
		// Set up our click listeners
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
		
		//String id = ((ViewGroup) v).getChildAt(1);
		
		//test.setText("Hello");
		
	}

}