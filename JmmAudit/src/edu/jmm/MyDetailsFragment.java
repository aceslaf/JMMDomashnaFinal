package edu.jmm;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyDetailsFragment extends Fragment {

	public static final String NUMBER = "Number";
	public static final String COLOR = "Color";

	private int number;
	//private int currentColor;
	private int defaultColor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		number = 0;
		if (savedInstanceState != null) {
		//	currentColor = savedInstanceState.getInt(COLOR);
			number = savedInstanceState.getInt(NUMBER, 0);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		defaultColor = getResources().getColor(android.R.color.darker_gray);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.details_fragment, container, false);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(NUMBER, number);
		//outState.putInt(COLOR, currentColor);
	}

	public void changeBackground(int i, int num) {
		TextView view = (TextView) getView().findViewById(R.id.detailsTextView);
		view.setText("Brojcheto e:" + num );
		number = num;
		//currentColor = i;
//		try {
//			view.setBackgroundColor(i);
//		} catch (IndexOutOfBoundsException e) {
//			view.setBackgroundColor(defaultColor);
//		}
	}

}