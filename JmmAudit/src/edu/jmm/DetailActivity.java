package edu.jmm;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			finish();
			return;
		}
		
	    setContentView(R.layout.fragment_activity_detail);
	    
		
	    
		TextView view = (TextView) findViewById(R.id.detailsTextView);
		Bundle extras = getIntent().getExtras();
	    
	    int number = 0;
		if (extras != null) {	     
	      number = extras.getInt(MyDetailsFragment.NUMBER);
	    }
		
		view.setText("Color changed" + number + "times");
	}
	
	

}
