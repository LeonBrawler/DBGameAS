package ru.samsung.itschool.dbgame;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	/*
	 * TABLES: ------- RESULTS SCORE INTEGER USER VARCHAR
	 */
	private Context context;
	private String DB_NAME = "game.db";

	private SQLiteDatabase db;

	private static DBManager dbManager;

	public static DBManager getInstance(Context context) {
		if (dbManager == null) {
			dbManager = new DBManager(context);
		}
		return dbManager;
	}

	private DBManager(Context context) {
		this.context = context;
		db = context.openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
		createTablesIfNeedBe(); 
	}

	void addResult(String username, int score) {
		db.execSQL("INSERT INTO RESULTS VALUES ('" + username + "', " + score
				+ ");");
	}
	// User1 150 User' , 150);DROP TABLE RESULTS; --
	// Запрос
	// INSERT INTO RESULTS VALUES('Player One', 150);


	public int gamesCount(){
		int cnt = 0;
		cnt = (int) DatabaseUtils.queryNumEntries(db, "RESULTS");
		//Cursor cursor = db.rawQuery("SELECT COUNT (*) FROM RESULTS;", null);   // ORDER BY score DESC
		//cursor.moveToFirst();
		//return cursor.getInt(0);
		return cnt;
	}

	public int maxNumber(){
		int maxN = 0;
		Cursor cursor = db.rawQuery("SELECT MAX (score) FROM RESULTS;", null);   // ORDER BY score DESC
		cursor.moveToFirst();
		return cursor.getInt(0);
	}

	public void cleanDataBase(){
		db.delete("RESULTS", null, null);
		//createTablesIfNeedBe();
	}

	ArrayList<Result> getAllResults() {
		ArrayList<Result> data = new ArrayList<Result>();
		Cursor cursor = db.rawQuery("SELECT * FROM RESULTS ORDER BY score DESC;", null);   // ORDER BY score DESC
		boolean hasMoreData = cursor.moveToFirst();

		while (hasMoreData) {
			String name = cursor.getString(cursor.getColumnIndex("USERNAME"));
			int score = Integer.parseInt(cursor.getString(cursor
					.getColumnIndex("SCORE")));
			data.add(new Result(name, score));
			hasMoreData = cursor.moveToNext();
		}

		return data;
	}

	private void createTablesIfNeedBe() {
		db.execSQL("CREATE TABLE IF NOT EXISTS RESULTS (USERNAME TEXT, SCORE INTEGER);");
	}

}

	// user1 150
	// INSERT INTO RESULTS VALUES('user1', 150);