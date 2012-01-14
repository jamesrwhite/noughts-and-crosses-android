package com.JamesWhite.NoughtsAndCrosses;

import org.json.JSONArray;

import android.content.Context;
import android.database.Cursor;

public class Database {
	
	private Context context;
	private LocalDatabase localDb;
	private RemoteDatabase remoteDb;
	
	public Database (Context context) {
		
		this.context = context;
		
	};
	
	private LocalDatabase localDb() {
		
		return new LocalDatabase(context, "", null, 0);
		
	}
	
	private RemoteDatabase remoteDb() {
		
		return new RemoteDatabase();
		
	}
	
	public void postScore(String name, int score, int date) {
		
		localDb = this.localDb();
		remoteDb = this.remoteDb();
		
		localDb.insertScore(name, score, date);
		remoteDb.insertScore(name, score, date);
		
	}
	
	public Cursor getLocalScores() {
		
		localDb = this.localDb();
		return localDb.getScores();
		
	}
	
	public JSONArray getGlobalScores() {
		
		remoteDb = this.remoteDb();
		return remoteDb.getJSONArray();
		
	}
	
	public void close() {
		
		localDb = this.localDb();
		
		localDb.close();
		
	}

}