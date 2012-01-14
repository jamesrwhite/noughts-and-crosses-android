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
	Button localHighScores;
	Button globalHighScores;
	
	TextView title;
	
	// Define our external font used in the game
	Typeface alphaMack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        newGame = (Button) findViewById(R.id.newGameButton);
        localHighScores = (Button) findViewById(R.id.newLocalHighScores);
        globalHighScores = (Button) findViewById(R.id.newGlobalHighScores);
        title = (TextView) findViewById(R.id.title);
        
        alphaMack = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/alphamack.ttf");
        
        title.setTypeface(alphaMack);
        
        newGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent gameIntent = new Intent(MenuActivity.this, GameActivity.class);
				startActivity(gameIntent);
				
			}
        	
        });
        
        localHighScores.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent localHighScoreIntent = new Intent(MenuActivity.this, LocalHighScoreActivity.class);
				startActivity(localHighScoreIntent);
				
			}
        	
        });
        
        globalHighScores.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent loadingGlobalHighScoreIntent = new Intent(MenuActivity.this, LoadingGloabalHighScoresActivity.class);
				startActivity(loadingGlobalHighScoreIntent);
				
			}
        	
        });
        
    }
    
}