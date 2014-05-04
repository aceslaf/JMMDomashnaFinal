package edu.jmm.sqlservicedemo;

import java.util.ArrayList;
import java.util.List;

//import com.google.android.gms.drive.internal.e;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.jmm.R;

public class StartUpActivity extends Activity implements MyResultReceiver.Receiver {

	private static final String TAG = "StartUpActivity";

	private MyResultReceiver mReceiver;
	
	private Button addNewEntry;
	private Button viewItems;
	
	private ItemDAO dao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_up);
	
		dao = new ItemDAO(this);
		
		addNewEntry = (Button) findViewById(R.id.addNewEntry);
		viewItems = (Button) findViewById(R.id.viewEntries);
		
		mReceiver = new MyResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        addNewEntry.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//start service if empty
                if(dao.getItems().size()==0){
			         final Intent intent = new Intent(getApplicationContext(), QueryService.class);
			         intent.putExtra("receiver", mReceiver);
			         intent.putExtra("command", "query");
			         startService(intent);
		        }else{//add new item if service started		        
			          String newEntry=String.valueOf( Math.random());                
	                  dao.insertNewItem(newEntry);
		        }
			}
		});
		
		viewItems.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent viewItems = new Intent(getApplicationContext(), DisplayItemsActivity.class);
				startActivity(viewItems);
				finish();
				dao.close();
				
			}
		});
		
	}
	
	
	public void onPause() {
        mReceiver.setReceiver(null);// clear receiver so no leaks.
        super.onPause();
    }

	public void onReceiveResult(int resultCode, Bundle resultData) {
		switch (resultCode) {
		case QueryService.STATUS_RUNNING:
			// show progress
			break;
		case QueryService.STATUS_FINISHED:
			ArrayList<Model> results = resultData.getParcelableArrayList("results");
			// do something interesting
			// hide progress
			
			Log.i(TAG, results.toString());
			
			for (Model model : results) {
				dao.insertNewItem(model.getTitle());
			}
			Toast.makeText(getApplicationContext(), "New Items added!", Toast.LENGTH_SHORT).show();
			
			
			break;
		case QueryService.STATUS_ERROR:
			// handle the error;
			Toast.makeText(getApplicationContext(), "Error ocurred...",
					Toast.LENGTH_LONG).show();

			Log.e(TAG, resultData.getString(Intent.EXTRA_TEXT));
			
			break;
		}
	}

}
