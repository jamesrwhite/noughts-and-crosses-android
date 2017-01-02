package com.JamesWhite.NoughtsAndCrosses;

/**
 * GlobalHighScoreActivity Holds the listview of the global high scores
 *
 * @author James White
 */
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GlobalHighScoreActivity extends ListActivity {

  @Override
  public void onCreate(Bundle savedInstance) {

    super.onCreate(savedInstance);
    setContentView(R.layout.globalhighscores);

    Intent intent = getIntent();
    String jsonString = intent.getStringExtra("jsonArray");
    JSONArray json = null;

    try {

      json = new JSONArray(jsonString);
      displayListView(json);

    } catch (JSONException e) {

      e.printStackTrace();
    }
  }

  /**
   * displayListView Handles populating the listview with our JSON data
   *
   * @param json
   */
  public void displayListView(JSONArray json) {

    // Build an ArrayList to store the scores
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    // Loop through the JSON and put it in our ArrayList
    try {

      // Loop the Array
      for (int i = 0; i < json.length(); i++) {

        HashMap<String, String> scores = new HashMap<String, String>();
        JSONObject e = json.getJSONObject(i);

        scores.put("id", String.valueOf(i));
        scores.put("name", e.getString("name"));
        scores.put("score", e.getString("score"));
        scores.put("date", e.getString("date"));

        list.add(scores);
      }

    }

    // Catch any JSON Exceptions and handle them
    catch (JSONException e) {

      Log.e("log_tag", "Error parsing data " + e.toString());
    }

    String[] from = new String[] {"name", "score"};
    int[] to = new int[] {R.id.globalHighScoreName, R.id.globalHighScoreInt};

    // Add it all to our ListAdapter
    ListAdapter highScoreAdapter =
        new SimpleAdapter(this, list, R.layout.globalhighscoreslistview, from, to);

    setListAdapter(highScoreAdapter);
  }
}
