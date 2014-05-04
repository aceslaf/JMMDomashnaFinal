package edu.jmm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ActivityLifeCycle extends Activity implements OnClickListener{
	
	private Button clearButton;
	private Button dowloadButton;
	private ImageView imgView;
	private Bitmap pic;

	private static final String TAG = "ActivityLifeCycle";
	
	public static final String DATA_PATH = Environment
			.getExternalStorageDirectory().toString() + /*File.pathSeparator+*/"/img.png";
	public static final String IMG_EXTENSION="png";	
	public static final String LOGKEY = "LogKey";
//	public static final String IMAGE_URL_LOCATION="http://upload.wikimedia.org/wikipedia/commons/6/66/Android_robot.png";
	public static final String IMAGE_URL_LOCATION="https://www.google.com/images/srpr/logo11w.png";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lifecycle);
		
		connectReferences();
		
		Resources res = getResources(); /** from an Activity */
		imgView.setImageDrawable(res.getDrawable(R.drawable.defimage));
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();		
		loadResources();
	}

	@Override
	protected void onPause() {
		super.onPause();		
		
	}

	@Override
	protected void onStop() {
		super.onStop();
		
			
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);		
		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);	
		
	}

	
	private void write(String methodName){
		
//		try{
//            
//            //File logTxt = new File(DATA_PATH, "log.txt");
//            FileWriter output = new FileWriter(DATA_PATH + "logTxt", true);
//            //output.write(logView.getText().toString());
//            output.write(methodName);
//
//            output.flush();
//            output.close();
//
//        } catch(IOException ioe){
//
//        	Log.e(TAG, "writing to external storage failed" + ioe.getMessage());
//        }
//		
	}
	public void connectReferences(){
		clearButton=(Button) findViewById(R.id.btnCancel);
		dowloadButton=(Button) findViewById(R.id.download);
		imgView=(ImageView) findViewById(R.id.fullImgView);
		clearButton.setOnClickListener(this);	
		dowloadButton.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if(isNetworkAvailable()){
					LoadFromInterMez loader=new LoadFromInterMez();
					loader.execute(IMAGE_URL_LOCATION);
					}else{
						Toast.makeText(getApplicationContext(), "Please enable internet", Toast.LENGTH_LONG);
					}
				} catch (Exception e) {
				}
			}
		});
		
	}
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	@Override
	public void onClick(View v) {
		clearImageAndHistory();
	}
	public void clearImageAndHistory(){
		File savedImgFile=new File(DATA_PATH);
		if(savedImgFile.delete()){			
			System.out.println("FILE DELETED");
		}else{
			System.out.println("no file to delete");
		}
		imgView.setImageResource(android.R.color.transparent);
	}
	public void loadResources(){
	     File imgFile=new File(DATA_PATH);
	     if(imgFile.exists()){
	    	 LoadFromFile loader= new LoadFromFile();
	    	 loader.execute(imgFile);
	    	 
	     }else{
	    	 Toast.makeText(getApplicationContext(), "Default picture used", Toast.LENGTH_SHORT).show();;
	     }
	}
	public void writeResourcesToFile(){
		
	}
	
	private class LoadFromFile extends AsyncTask<File, Integer, Bitmap>{
         
		

		@Override
		protected void onPostExecute(final Bitmap result) {
			imgView.post(new Runnable() {
				
				@Override
				public void run() {
					imgView.setImageBitmap(result);
					Toast.makeText(getApplicationContext(), "image downloaded from file", Toast.LENGTH_LONG).show();
					
				}
			});
			super.onPostExecute(result);
		}

		

		@Override
		protected Bitmap doInBackground(File... files) {
			File file=files[0];
			FileOutputStream out;
			if(file==null){
				return null;
			}
			
			Bitmap bitmap;
			try {
				bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
				
			   
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
				return null;
			}
			pic=bitmap;
			return bitmap;
		}
		
	}
      private class LoadFromInterMez extends AsyncTask<String, Integer, Bitmap>{
         
		

		@Override
		protected void onPostExecute(final Bitmap result) {
			imgView.post(new Runnable() {
				
				@Override
				public void run() {
					imgView.setImageBitmap(result);
					Toast.makeText(getApplicationContext(), "image loaded from intermez", Toast.LENGTH_LONG).show();
					
				}
			});
			super.onPostExecute(result);
		}

		

		@Override
		protected Bitmap doInBackground(String... locations) {
		    String location= locations[0];
		    if(location==null){
		    	return null;
		    }
		    		
		    
			Bitmap bitmap=null;
			URL url;
			URLConnection conn;
			InputStream in;
			FileOutputStream out;
			try {
				File yourFile = new File(DATA_PATH);
				if(!yourFile.exists()) {
				    yourFile.createNewFile();
				} 
				out = new FileOutputStream(yourFile,false);
				url = new URL("https://www.google.com/images/srpr/logo11w.png");
				
			    conn = url.openConnection();
			    in = conn.getInputStream();
			
				bitmap = BitmapFactory.decodeStream(in);
				bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
				return null;
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pic=bitmap;
			return bitmap;
		}
		
	}
	
}

























