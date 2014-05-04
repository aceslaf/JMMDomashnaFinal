package edu.jmm.sqlservicedemo;

import android.app.Activity;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.jmm.MainActivity;
import edu.jmm.R;

public class DisplayItemsActivity extends Activity {

	private static final int MY_NOTIFICATION_ID = 1;
	private ItemDAO dataAccessObject;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_items);
		
		//ListView list = (ListView) findViewById(R.id.list);
		// create the data access object
		dataAccessObject = new ItemDAO(this);
		
	    
		textView=(TextView)findViewById(R.id.itemsTextView);
		StringBuilder pom = new StringBuilder();
		for(Model model: dataAccessObject.getItems()){
			pom.append("id:"+model.getId()+" "+model.getTitle()+"\n");			
		}
		textView.setText(pom.toString());
//		Button backButton = (Button)findViewById(R.id.back);
//		backButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent back = new Intent(getApplicationContext(), StartUpActivity.class);
//				startActivity(back);
//				finish();
//			}
//		});
	}
	
	

}
