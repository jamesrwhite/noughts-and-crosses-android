package com.JamesWhite.NoughtsAndCrosses;

/**
 * MenuActivity Handles showing the games main menu and switching to other activites
 *
 * @author James White
 */
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
  TextView text;
  TextView title;

  // Define our external font used in the game
  Typeface alphaMack;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.menu);

    // Define our buttons and title
    newGame = (Button) findViewById(R.id.newGameButton);
    localHighScores = (Button) findViewById(R.id.newLocalHighScores);
    globalHighScores = (Button) findViewById(R.id.newGlobalHighScores);
    title = (TextView) findViewById(R.id.title);

    // set up our custom typeface
    alphaMack =
        Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/alphamack.ttf");
    title.setTypeface(alphaMack);

    // Switch to the game activity
    newGame.setOnClickListener(
        new View.OnClickListener() {

          @Override
          public void onClick(View v) {

            Intent gameIntent = new Intent(MenuActivity.this, GameActivity.class);
            startActivity(gameIntent);
          }
        });

    // Switch to the local high scores activity
    localHighScores.setOnClickListener(
        new View.OnClickListener() {

          @Override
          public void onClick(View v) {

            Intent localHighScoreIntent =
                new Intent(MenuActivity.this, LocalHighScoreActivity.class);
            startActivity(localHighScoreIntent);
          }
        });

    // Switch to the global high scores activity
    globalHighScores.setOnClickListener(
        new View.OnClickListener() {

          @Override
          public void onClick(View v) {

            Intent loadingGlobalHighScoreIntent =
                new Intent(MenuActivity.this, LoadingGloabalHighScoresActivity.class);
            startActivity(loadingGlobalHighScoreIntent);
          }
        });
  }
}
