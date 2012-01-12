package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends Activity {
	
	Button newGame;
	Button highScores;
	
	TextView title;
	
	// Define our external font used in the game
	Typeface alphaMack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        newGame = (Button) findViewById(R.id.newGameButton);
        highScores = (Button) findViewById(R.id.newHighScores);
        title = (TextView) findViewById(R.id.title);
        
        alphaMack = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/alphamack.ttf");
        
        title.setTypeface(alphaMack);
        
        newGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent gameIntent = new Intent(MenuActivity.this, GameActivity.class);
				MenuActivity.this.startActivity(gameIntent);
				
			}
        	
        });
        
        highScores.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent highScoreIntent = new Intent(MenuActivity.this, HighScoreActivity.class);
				MenuActivity.this.startActivity(highScoreIntent);
				
			}
        	
        });
        
    }
    
}