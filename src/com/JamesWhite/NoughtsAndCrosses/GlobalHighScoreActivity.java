package com.JamesWhite.NoughtsAndCrosses;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

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

		}

		catch (JSONException e) {
			
			e.printStackTrace();
		}

	}

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

		catch (JSONException e) {

			Log.e("log_tag", "Error parsing data " + e.toString());

		}

		String[] from = new String[] { "name", "score" };
		int[] to = new int[] { R.id.globalHighScoreName,
				R.id.globalHighScoreInt };

		// Add it all to our ListAdapter
		ListAdapter highScoreAdapter = new SimpleAdapter(this, list,
				R.layout.globalhighscoreslistview, from, to);

		setListAdapter(highScoreAdapter);

	}

}