package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        // Create our players
        Player human = new Player();
        Player computer = new Player();
        human.setType(1);
        computer.setType(0);
        
        // Let the games begin!
        Game game = new Game();
        game.setup();
        
    }
    
}