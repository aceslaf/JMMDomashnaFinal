package edu.jmm.sqlservicedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemSQLiteHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "links_db2";
	public static final String TABLE_NAME = "links";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_TITLE = "title";
	
	
	public ItemSQLiteHelper(Context context) {
		// create table "notes_db": version 1
		super(context, ItemSQLiteHelper.DATABASE_NAME, null, 1);
	}

	/**
	 * Create a simple table
	 * table name: "links"
	 * columns: id (primary key) INTEGER
	 * 			title TEXT
	 * 			link TEXT 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// execute create table sql statement
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// drop existing table
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
		// recreate the table
		onCreate(db);
	}

}
