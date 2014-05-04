package edu.jmm.sqlservicedemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

public class Downloader {

	private static final String TAG = "Downloader";

	static final String MAIN_TAG = "cd";
	static final String KEY_TITLE = "title";
	static final String KEY_LINK = "artist";

	public static ArrayList<Model> download(String xmlUrl) {

		Log.i(TAG, "starting download");
		ArrayList<Model> items = new ArrayList<Model>();

		// temp holder for current text value while parsing
		String curText = "";

		try {
			
			URL url = new URL(xmlUrl);
			
			// Create a new HTTP URL connection
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;

			int responseCode = httpConnection.getResponseCode();


			Log.i(TAG, "HTTP connection open");
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream in = httpConnection.getInputStream();

				Log.i(TAG, "xml downloaded" + in.toString());
				
//				StringBuilder sb = new StringBuilder();
//				BufferedReader bf = new BufferedReader(new InputStreamReader(in));
//				while(bf.readLine() != null){
//					sb.append(bf.readLine());
//				}
//				
//				Log.e(TAG, sb.toString());
//				
				
				XmlPullParserFactory factory;
				try {
					factory = XmlPullParserFactory.newInstance();
					factory.setNamespaceAware(true);
					XmlPullParser xpp = factory.newPullParser();
					xpp.setInput(in, null);
					Log.e(TAG, "parsing document");

					xpp.next();
					
					int eventType = xpp.getEventType();
			        String currentTag = null;
			        String title = null;
			        String link = null;
			        while (eventType != XmlPullParser.END_DOCUMENT) {
			            currentTag = xpp.getName();
			        	if(currentTag==null)
			        		curText = xpp.getText();
			            
			            switch(eventType){
			            	case XmlPullParser.START_TAG:
			            		Log.e(TAG, "currentTag: " + currentTag);
			            		break;
			            		
			            	case XmlPullParser.TEXT:
			            		break;
			            						            
			            	default:
			            		if(currentTag.equalsIgnoreCase(KEY_TITLE)) {
				                    title = curText;
				                    Log.e(TAG, "currentText: " + title);
				            	}
			            		
			            		if(currentTag.equalsIgnoreCase(KEY_LINK)) {
				                    link = curText;
				                    Log.i(TAG, "currentText: " + link);
				            	}
			            		if(currentTag.equalsIgnoreCase(MAIN_TAG)) {
				            		Log.e(TAG, "ending current tag: " + currentTag + "Title: " + title + "Link" + link);	
				            		Model model = new Model();				            		
				            		model.setTitle(title);
				            		items.add(model);
				            		Log.e(TAG, "New item added: " + model.toString());
				            	}
			            }
			            
			            eventType = xpp.next();
			        }
				
				} catch (Exception e) {
					Log.e(TAG, "Error in parsing" + e.getMessage());
				}

			}else{
				Log.e(TAG, "HTTP responce code:" + responseCode );
			}

		} catch (MalformedURLException e) {
			Log.e(TAG, "Malformed URL Exception.");
		} catch (IOException e) {
			Log.e(TAG, "IO Exception.");
		}
		
		
		Log.e(TAG, items.toString());
		
		return items;
	}


}
