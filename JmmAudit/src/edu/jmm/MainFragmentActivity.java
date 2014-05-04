package edu.jmm;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;

public class MainFragmentActivity extends Activity implements
		MyListFragment.IncrementListener {

	private int number;
	//private int colors[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragment_layout);
		number = 0;
//		colors = new int[] {
//				getApplicationContext().getResources()
//						.getColor(R.color.green),
//				getApplicationContext().getResources()
//						.getColor(R.color.red),
//				getApplicationContext().getResources()
//						.getColor(R.color.orange),
//				getApplicationContext().getResources()
//						.getColor(R.color.yellow), };

	}



	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

		number = savedInstanceState.getInt(MyDetailsFragment.NUMBER, 0);
	}



	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		outState.putInt(MyDetailsFragment.NUMBER, number);
	}



	@Override
	public void onIncrement(int i) {
		MyDetailsFragment fragment = (MyDetailsFragment) getFragmentManager()
				.findFragmentById(R.id.detailFragment);
		number++;

		// if (fragment != null && fragment.isInLayout()) {
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			fragment.changeBackground(0,number);
		} else {
			Intent intent = new Intent(getApplicationContext(),
					DetailActivity.class);
			intent.putExtra(MyDetailsFragment.COLOR,0);
			intent.putExtra(MyDetailsFragment.NUMBER, number);
			startActivity(intent);
		}

	}

}