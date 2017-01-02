package com.JamesWhite.NoughtsAndCrosses;

/**
 * LocalDatabase Handles interacting with a local SQLite database
 *
 * @author James White
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDatabase extends SQLiteOpenHelper {

  static final String databaseName = "noughtsAndCrosses";
  static final String highScoresTable = "highScores";
  static final String colScoreID = "scoreID";
  static final String colName = "name";
  static final String colScore = "score";
  static final String colDate = "date";

  public LocalDatabase(Context context, String name, CursorFactory factory, int version) {

    super(context, databaseName, null, 4);
  }

  /**
   * onCreate Overridden to create our table
   *
   * @param db
   */
  @Override
  public void onCreate(SQLiteDatabase db) {

    db.execSQL(
        "CREATE TABLE "
            + highScoresTable
            + "( "
            + colScoreID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + colName
            + " TEXT, "
            + colScore
            + " INTEGER, "
            + colDate
            + " INTEGER "
            + " );");
  }

  /**
   * onUpgrade Overridden to upgrade our databae if needed
   *
   * @param db
   * @param oldVersion
   * @param newVersion
   */
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("DROP TABLE IF EXISTS " + highScoresTable);

    onCreate(db);
  }

  /**
   * insertScore insert the score data passed to us into the database
   *
   * @param name
   * @param score
   * @param date
   */
  public void insertScore(String name, int score, int date) {

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cv = new ContentValues();

    cv.put(colName, name);
    cv.put(colScore, score);
    cv.put(colDate, date);

    db.insert(highScoresTable, colScoreID, cv);

    db.close();
  }

  /**
   * getScores returns the high scores as a cursor
   *
   * @return cursor
   */
  public Cursor getScores() {

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor =
        db.rawQuery(
            "SELECT "
                + colScoreID
                + " as _id, "
                + colName
                + ", "
                + colScore
                + " FROM "
                + highScoresTable
                + " ORDER BY "
                + colScore
                + " ASC",
            new String[] {});

    return cursor;
  }
}
