package edu.jmm;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import edu.jmm.contentproviderdemo.ContentProviderActivity;
import edu.jmm.intentsdemo.FetchPhotoActivity;
import edu.jmm.sqlservicedemo.StartUpActivity;

public class MainActivity extends Activity {

	private static final int NOTIFICATION_ID = 1243;
	private Button fragmentActivityButton;
	private Button sqliteActivityButton;
	private Button activityLifecycleButton;
	private Button intentBcastButton;
	private Button contentProviderButton;
	private Button notificationButton;
	private Button btnSensor;
	
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;
	private int mNotificationCount;
	private long[] mVibratePattern = { 0, 200, 200, 300 };

	private NotificationManager mNotificationManager;
    private String mMessage;
    private int mMillis;
    Notification.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String value = prefs.getString(PreferencesActivity.VALUE, "Current value");
		Toast.makeText(this, "setting string is: "+value, Toast.LENGTH_LONG).show();
		
		
		
		registerListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.openPreferences:
			Intent i = new Intent(getApplicationContext(), PreferencesActivity.class);
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// separate the code of registering event-listeners
	private void registerListeners() {
        btnSensor=(Button) findViewById(R.id.btnSensor);
		fragmentActivityButton = (Button) findViewById(R.id.fragmentActivityButton);
		activityLifecycleButton = (Button) findViewById(R.id.activityLifecycleButton);
		sqliteActivityButton = (Button) findViewById(R.id.sqliteButton);
		intentBcastButton = (Button) findViewById(R.id.intentActivityButton);
		contentProviderButton = (Button) findViewById(R.id.contentProviderButton);
		//serviceInternetMapsButton = (Button) findViewById(R.id.MapsButton);
        notificationButton=(Button) findViewById(R.id.NotifyMe);
       
        
		fragmentActivityButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent fragIntent = new Intent(getApplicationContext(),
						MainFragmentActivity.class);
				startActivity(fragIntent);
			}
		});

		intentBcastButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent fetchIntent = new Intent(getApplicationContext(),
						FetchPhotoActivity.class);
				startActivity(fetchIntent);
			}
		});

		activityLifecycleButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent lifeCycleIntent = new Intent(getApplicationContext(),
						ActivityLifeCycle.class);
				startActivity(lifeCycleIntent);
			}
		});

		contentProviderButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent contProv = new Intent(getApplicationContext(),
						ContentProviderActivity.class);
				startActivity(contProv);
			}
		});

		sqliteActivityButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent notepadIntent = new Intent(getApplicationContext(), StartUpActivity.class);
				startActivity(notepadIntent);
			}
		});
		
		notificationButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mNotificationIntent = new Intent(getApplicationContext(),
						MyDetailsFragment.class);
				mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
						mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
//				
                   Notification notification;
                   Notification.Builder builder= new Notification.Builder(getApplicationContext());
                   builder
	                   .setContentTitle("Naslov")
	                   .setContentText("obichen text")
	                   .setTicker("Dolg dolg tekst koj kje mora da se skrola za da se prikaze, METHOD USED: setTicker(String)")
	                   .setSmallIcon(android.R.drawable.stat_sys_warning)
	                   .setAutoCancel(true)					
					   .setContentIntent(mContentIntent)					   
					   .setVibrate(mVibratePattern);
                   
                   NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                   notification=builder.getNotification();
                   manager.notify(NOTIFICATION_ID,notification);
                   
			}
			
		});
		
		btnSensor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent fetchIntent = new Intent(getApplicationContext(),
						DrawableAcc.class);
				startActivity(fetchIntent);
			}
		});
		
		
//		serviceInternetMapsButton.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				Intent simd = new Intent(getApplicationContext(), MapActivity.class);
//				startActivity(simd);
//			}
//		});

	}

}
