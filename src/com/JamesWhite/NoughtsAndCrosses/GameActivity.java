package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity implements OnClickListener {
	
	// Define all the grid cells
	LinearLayout cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8;
	
	// Define the onClick parent and child views
	LinearLayout parent;
	TextView child;
	
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
		human.setType(0);
		computer.setType(1);
		
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
		
		parent = (LinearLayout) findViewById(v.getId());
		child = (TextView) parent.getChildAt(0);
		
		if( human.getType() == game.getCross() ) {
			
			child.setText("X");
			
		}
		
		else {
			
			child.setText("0");
			
		}
		
	}

}