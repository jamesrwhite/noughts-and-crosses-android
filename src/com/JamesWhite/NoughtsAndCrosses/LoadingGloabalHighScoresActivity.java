package com.JamesWhite.NoughtsAndCrosses;

import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class LoadingGloabalHighScoresActivity extends Activity {

	private ProgressDialog progress;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.globalhighscores);

		progress = new ProgressDialog(this);
		progress.setMessage("Loading...");
		new MyTask(progress).execute();

	}

	public class MyTask extends AsyncTask<Context, Void, JSONArray> {

		private ProgressDialog progress;

		public MyTask(ProgressDialog progress) {

			this.progress = progress;

		}

		@Override
		protected JSONArray doInBackground(Context... params) {

			// Get the JSON using our RemoteDatabase class
			Database db = new Database(getApplicationContext());
			JSONArray json = db.getGlobalScores();

			return json;

		}

		public void onPreExecute() {

			progress.show();

		}

		public void onPostExecute(JSONArray result) {
			
			// Hide the progress box
			progress.dismiss();

			// Pass the JSON back to our activity
			Intent globalHighScoreIntent = new Intent(LoadingGloabalHighScoresActivity.this, GlobalHighScoreActivity.class);
			globalHighScoreIntent.putExtra("jsonArray", result.toString());
			startActivity(globalHighScoreIntent);

		}

	}

}