package edu.jmm.sqlservicedemo;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

public class ItemDAO {

	private SQLiteDatabase db;
	private ItemSQLiteHelper dbHelper;
	
	public ItemDAO(Context context){
		dbHelper = new ItemSQLiteHelper(context);
		db = dbHelper.getWritableDatabase();
	}
	
	/**
	 * Insert a new item into the database
	 * @param itemTitle
	 * @param itemLink
	 */
	public void insertNewItem(String itemTitle){
		ContentValues contentValues = new ContentValues();		
		contentValues.put(ItemSQLiteHelper.COLUMN_TITLE, itemTitle);	
		
		db.insert(ItemSQLiteHelper.TABLE_NAME, null, contentValues);
	}
	
	/**
	 * Get all the items from the database
	 * @return list of items to populate the {@link ListView}
	 */
	public List<Model> getItems(){
		List<Model> items = new ArrayList<Model>();
		
		String[] tableColumns = new String[] {
			ItemSQLiteHelper.COLUMN_ID,
			ItemSQLiteHelper.COLUMN_TITLE,			
		};
		
		Cursor cursor = db.query(ItemSQLiteHelper.TABLE_NAME, tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		while(!cursor.isAfterLast()){
			//create new model object item
			Model note = new Model();
			
			//set items' values
			note.setId(cursor.getInt(0));
			note.setTitle(cursor.getString(1));		
			
			//add the new item to the list
			items.add(note);
			
			//move to next result of the query
			cursor.moveToNext();
		}
		
		return items;
	}
	
	/**
	 * Delete an item from the database
	 * @param itemId the id of the item
	 */
	public void deleteItem(int itemId){
		db.delete(ItemSQLiteHelper.TABLE_NAME, ItemSQLiteHelper.COLUMN_ID + " = " + itemId, null);
	}
	
	//close the database
	public void close(){
		db.close();
	}
	
}
