package com.JamesWhite.NoughtsAndCrosses;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class HighScoreActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstance) {

		super.onCreate(savedInstance);
		setContentView(R.layout.highscores);

		LocalDatabase localDatabase = new LocalDatabase(
				getApplicationContext(), "noughtsAndCrosses", null, 4);
		Cursor cursor = localDatabase.getScores();
		startManagingCursor(cursor);

		// the desired columns to be bound
		String[] columns = new String[] { "name", "score" };

		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.highScoreName, R.id.highScoreInt };

		// create the adapter using the cursor pointing to the desired data as
		// well as the layout information
		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,
				R.layout.highscoreslistview, cursor, columns, to);

		// set this adapter as your ListActivity's adapter
		this.setListAdapter(mAdapter);

		//cursor.close();
		localDatabase.close();

	}

}