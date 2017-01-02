package com.JamesWhite.NoughtsAndCrosses;

/**
 * LoadingGlobalHighScoresActivity Handles loading the global high scores JSON async
 *
 * @author James White
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import org.json.JSONArray;

public class LoadingGloabalHighScoresActivity extends Activity {

  private ProgressDialog progress;

  @Override
  public void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.globalhighscores);

    progress = new ProgressDialog(this);
    progress.setMessage("Loading...");
    new AsyncLoadJSON(progress).execute();
  }

  public class AsyncLoadJSON extends AsyncTask<Context, Void, JSONArray> {

    private ProgressDialog progress;

    public AsyncLoadJSON(ProgressDialog progress) {

      this.progress = progress;
    }

    /**
     * doInBackground Overridden to change data type
     *
     * @return json
     * @author James White
     */
    @Override
    protected JSONArray doInBackground(Context... params) {

      // Get the JSON using our RemoteDatabase class
      Database db = new Database(getApplicationContext());
      JSONArray json = db.getGlobalScores();

      return json;
    }

    /**
     * onPreExecute Shows progress spinner
     *
     * @author James White
     */
    public void onPreExecute() {

      progress.show();
    }

    /**
     * onPostExecute Overridden to change data type
     *
     * @return result the JSON
     * @author James White
     */
    public void onPostExecute(JSONArray result) {

      // Hide the progress box
      progress.dismiss();

      // Finish this activity
      finish();

      // Pass the JSON back to our activity
      Intent globalHighScoreIntent =
          new Intent(LoadingGloabalHighScoresActivity.this, GlobalHighScoreActivity.class);
      globalHighScoreIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      globalHighScoreIntent.putExtra("jsonArray", result.toString());
      startActivity(globalHighScoreIntent);
    }
  }
}
