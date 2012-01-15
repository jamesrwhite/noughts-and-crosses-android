package com.JamesWhite.NoughtsAndCrosses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class AsyncSubmitGloabalHighScoresActivity extends Activity {

	private ProgressDialog progress;
	String name, scoreString, dateString;
	int score, date;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		
		// Get the data passed to us from the game Activity
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		scoreString = intent.getStringExtra("score");
		dateString = intent.getStringExtra("date");
		
		// Parse the integers passed over as strings
		score = Integer.parseInt(scoreString);
		date = Integer.parseInt(dateString);
		
		// Start the Async task!
		progress = new ProgressDialog(this);
		progress.setMessage("Submitting Score...");
		new MyTask(progress).execute();

	}

	public class MyTask extends AsyncTask<Context, Void, String> {

		private ProgressDialog progress;

		public MyTask(ProgressDialog progress) {

			this.progress = progress;

		}

		@Override
		protected String doInBackground(Context... params) {

			Database db = new Database(getApplicationContext());
			String scoreReturn = db.postScore(name, score, date);
			db.close();

			return scoreReturn;

		}
		
		@Override
		public void onPreExecute() {

			progress.show();

		}
		
		@Override
		public void onPostExecute(String scoreReturn) {
			
			// Hide the progress box
			progress.dismiss();
			
			// Finish this activity
			finish();

			// Pass the JSON back to our activity
			Intent menuIntent = new Intent(AsyncSubmitGloabalHighScoresActivity.this, MenuActivity.class);
			menuIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(menuIntent);

		}

	}

}