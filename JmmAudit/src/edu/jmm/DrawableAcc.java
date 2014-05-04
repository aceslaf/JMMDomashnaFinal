package edu.jmm;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class DrawableAcc extends Activity  implements ICoordProvider, SensorEventListener{
	private float x;
	private float y;
	private float z;
	private static final int UPDATE_THRESHOLD = 30;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private long mLastUpdate;
	private Ball ball;
	 @Override
	   public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	        //  Get reference to SensorManager
			mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

			// Get reference to Accelerometer
			if (null == (mAccelerometer = mSensorManager
					.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)))
				finish();
			
	      ball = new Ball(this,this);
	      setContentView(ball);
	      ball.setBackgroundColor(Color.BLACK);
	   }
	 
	 @Override
		protected void onResume() {
			super.onResume();

			mSensorManager.registerListener(this, mAccelerometer,
					SensorManager.SENSOR_DELAY_UI);

			mLastUpdate = System.currentTimeMillis();

		}


	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public float getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(this);
		super.onPause();
	}

	// Process new reading
	@Override
	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

			long actualTime = System.currentTimeMillis();

			if (actualTime - mLastUpdate > UPDATE_THRESHOLD) {

				mLastUpdate = actualTime;

				      x = event.values[0];
				      y = event.values[1];
				      z = event.values[2];			

			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
}
