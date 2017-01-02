package com.JamesWhite.NoughtsAndCrosses;

/**
 * Database
 *
 * @author James White
 *     <p>Parent class of the two databases, makes it easier to submit/retrieve scores
 */
import android.content.Context;
import android.database.Cursor;
import org.json.JSONArray;

public class Database {

  private Context context;
  private LocalDatabase localDb;
  private RemoteDatabase remoteDb;

  public Database(Context context) {

    this.context = context;
  };

  /**
   * localDb returns a LocalDatabase object
   *
   * @return localDb a LocalDatabase object
   * @author James White
   */
  private LocalDatabase localDb() {

    return new LocalDatabase(context, "", null, 0);
  }

  /**
   * remoteDb returns a LocalDatabase object
   *
   * @return remoteDb a RemoteDatabae object
   * @author James White
   */
  private RemoteDatabase remoteDb() {

    return new RemoteDatabase();
  }

  /**
   * postScore handles posting a score to both the local and remote databases
   *
   * @param name player name
   * @param score the score to be inserted
   * @param date the date of the game
   * @author James White
   */
  public String postScore(String name, int score, int date) {

    localDb = this.localDb();
    remoteDb = this.remoteDb();

    localDb.insertScore(name, score, date);
    String returnRemote = remoteDb.insertScore(name, score, date);

    localDb.close();

    return returnRemote;
  }

  /**
   * getLocalScores accessor method for getting the LocalScores Cursor
   *
   * @return localDb
   * @author James White
   */
  public Cursor getLocalScores() {

    localDb = this.localDb();
    return localDb.getScores();
  }

  /**
   * getGlobalScores accessor method for getting the JSON returned from the RemoteDatabase HTTP POST
   * request to the server
   *
   * @return json
   * @author James White
   */
  public JSONArray getGlobalScores() {

    remoteDb = this.remoteDb();
    return remoteDb.getJSONArray();
  }

  /**
   * close self explanatory, close the SQLite database to prevent leaks
   *
   * @author James White
   */
  public void close() {

    localDb = this.localDb();
    localDb.close();
  }
}
