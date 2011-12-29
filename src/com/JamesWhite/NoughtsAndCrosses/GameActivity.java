package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        // Let the games begin!
        Game game = new Game();
        game.setup();
        
    }
    
}