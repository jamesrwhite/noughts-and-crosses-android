package com.JamesWhite.NoughtsAndCrosses;

/**
 * LocalHighScoreActivity Handles creating and setting up the ListView to hold the Local high scores
 * 
 * @author James White
 */

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class LocalHighScoreActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstance) {

		super.onCreate(savedInstance);
		setContentView(R.layout.localhighscores);

		LocalDatabase localDatabase = new LocalDatabase(
				getApplicationContext(), "noughtsAndCrosses", null, 4);
		Cursor cursor = localDatabase.getScores();
		startManagingCursor(cursor);

		// Columns names from the db
		String[] columns = new String[] { "name", "score" };

		// the XML views in our layout that the data will be placed in
		int[] to = new int[] { R.id.highScoreName, R.id.highScoreInt };

		// create the adapter using the cursor
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.localhighscoreslistview, cursor, columns, to);

		this.setListAdapter(adapter);

		localDatabase.close();

	}

}