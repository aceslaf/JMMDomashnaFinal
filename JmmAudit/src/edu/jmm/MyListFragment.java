package edu.jmm;

import java.util.Random;

import edu.jmm.R;
import edu.jmm.R.id;
import edu.jmm.R.layout;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyListFragment extends Fragment {

	private IncrementListener listener;
	private Button updateButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.list_fragment,
				container, false);
		updateButton = (Button) view.findViewById(R.id.updateRandom);
		updateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Random r = new Random();
				int i = r.nextInt(4);

				listener.onIncrement(i);
			}
		});
		return view;
	}

	public interface IncrementListener {
		public void onIncrement(int i);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (activity instanceof IncrementListener) {
			listener = (IncrementListener) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implemenet MyListFragment.IncrementListener");
		}
	}

}