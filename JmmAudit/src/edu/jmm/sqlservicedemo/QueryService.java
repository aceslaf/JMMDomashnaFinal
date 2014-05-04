package edu.jmm.sqlservicedemo;

import java.util.ArrayList;
import java.util.List;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class QueryService extends IntentService {
    
	public static final int STATUS_RUNNING = 1;
	public static final int STATUS_ERROR = 2;
	public static final int STATUS_FINISHED = 3;
	
	//public static final String URL = "http://feeds.feedburner.com/techcrunch/android?format=xml";
	public static final String URL = "http://www.w3schools.com/xml/cd_catalog.xml";
	private static final String TAG = "QueryService";
	
	public QueryService(){
		super("QueryService");
	}
	
	public QueryService(String name) {
		super(name);
	}

	protected void onHandleIntent(Intent intent) {
		
		Log.i(TAG, "Service started...");
		
        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String command = intent.getStringExtra("command");
        Bundle b = new Bundle();
        if(command.equals("query")) {
        	
        	Log.i(TAG, "serving query request");
        	
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
            try {
                // get some data or something
            	Log.i(TAG, "requesting Empty arrayList");
            	//Simulate downloading from the internet;
            	Thread.sleep(2000);
            	ArrayList<Model> results = new ArrayList<Model>();
            	Model hardkodiranModel=new Model();
            	hardkodiranModel.setTitle("Item, bozemski simnat od net");
            	results.add(hardkodiranModel);
            	
            	
            	Log.i(TAG, results.toString());
            	
                b.putParcelableArrayList("results", results);
                receiver.send(STATUS_FINISHED, b);
            } catch(Exception e) {
            	Log.i(TAG, "error in download" + e.getMessage());
                b.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, b);
            }    
        }
        this.stopSelf();
    }
}