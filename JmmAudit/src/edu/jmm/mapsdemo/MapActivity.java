package edu.jmm.mapsdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
//import com.google.android.gms.maps.MapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;

import edu.jmm.R;

public class MapActivity extends Activity {

	public static final String API_ENDPOINT = "https://api.foursquare.com/v2/venues/trending";

	//got via web access, changed on daily basis
	public static final String AUTH_TOKEN = "WELX04QLWJC3BE1MBJAFEDQIWSWQLLFCTLJ1J20SGBJF3BDZ&v=20140426";

	private static final String TAG = "MapActivity";

	//private GoogleMap map;
	private List<Location> locations;
	private Button calculateDistance;
	private Button restartMarkers;
	private int i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.maps);
//		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//		
//		map.addMarker(new MarkerOptions()
//        .position(new LatLng(41.986595, 21.422476))
//        .title("Home"));
//		
//		i = 1;
//		locations = new ArrayList<Location>();
//		
//		map.setOnMapClickListener(new OnMapClickListener() {
//			
//			@Override
//			public void onMapClick(LatLng arg0) {
//				double lat = arg0.latitude;
//				double lon = arg0.longitude;
//				
//				map.addMarker(new MarkerOptions()
//		        .position(arg0)
//		        .title("JMMMarker"+i));
//				i++;
//				
//				Location newLoc = new Location("");
//				newLoc.setLatitude(lat);
//				newLoc.setLongitude(lon);
//				locations.add(newLoc);
//			}
//		});
//		
//		calculateDistance = (Button) findViewById(R.id.calculateDistanceButton);
//		restartMarkers = (Button) findViewById(R.id.resetMap);
//		
//		calculateDistance.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				double distance = 0.0;
//				if(locations.size()>1){
//					for (int i=0; i<locations.size()-1; i++){
//						distance += locations.get(i).distanceTo(locations.get(i+1));
//					}
//					distance+=locations.get(locations.size()-1).distanceTo(locations.get(0));
//					Toast.makeText(getApplicationContext(), String.format("Total distance between locations is: %.2f m", distance), Toast.LENGTH_SHORT).show();
//				} else {
//					Toast.makeText(getApplicationContext(), "Assign at least two locations...", Toast.LENGTH_SHORT).show();
//				}
//			}
//			
//		});
//		
//		restartMarkers.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				map.clear();
//				locations.clear();
//				i = 0;
//				Toast.makeText(getApplicationContext(), "All assigned locations cleared...", Toast.LENGTH_SHORT).show();
//			}
//			
//		});
//		
//		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//		// Define a listener that responds to location updates
//		LocationListener locationListener = new LocationListener() {
//			// Called when a new location is found by the network location provider.
//		    public void onLocationChanged(Location location) {
////		    	
////		    	double lat = location.getLatitude();
////		    	double lon = location.getLongitude();
////		    	
////				map.addMarker(new MarkerOptions()
////		        .position(new LatLng(lat, lon))
////		        .title("GPS-Set"));
////				
////				
//		    }
//
//		    public void onStatusChanged(String provider, int status, Bundle extras) {
//		    	Toast.makeText(getApplicationContext(), provider + " status changed...", Toast.LENGTH_SHORT).show();
//		    }
//
//		    public void onProviderEnabled(String provider) {
//		    	Toast.makeText(getApplicationContext(), provider + " enabled...", Toast.LENGTH_SHORT).show();
//		    }
//
//		    public void onProviderDisabled(String provider) {
//		    	Toast.makeText(getApplicationContext(), provider + " disabled...", Toast.LENGTH_SHORT).show();
//		    }
//		  };
//
//		// Register the listener with the Location Manager to receive location updates
//		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);		
	}
	
}
