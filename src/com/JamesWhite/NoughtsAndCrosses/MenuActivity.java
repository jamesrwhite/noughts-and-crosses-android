package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {
	
	Button newGame;
	Button highScores;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        newGame = (Button) findViewById(R.id.newGameButton);
        highScores = (Button) findViewById(R.id.newHighScores);
        
        newGame.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MenuActivity.this, GameActivity.class);
				MenuActivity.this.startActivity(intent);
				
			}
        	
        });
        
        highScores.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MenuActivity.this, HighScoreActivity.class);
				MenuActivity.this.startActivity(intent);
				
			}
        	
        });
        
    }
    
}