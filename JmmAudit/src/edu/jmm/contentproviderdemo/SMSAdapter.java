package edu.jmm.contentproviderdemo;

import java.util.List;

import edu.jmm.R;
import edu.jmm.R.id;
import edu.jmm.R.layout;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SMSAdapter extends ArrayAdapter<String> {

	private final Context context;
	private List<String> items;
	private LayoutInflater inflater;

	public SMSAdapter(Context context, List<String> resource) {
		super(context, R.layout.content_provider_main, resource);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		items = resource;
		this.context = context;
	}

	class ViewHolder {
		RelativeLayout layout;
		TextView dateText;
		TextView bodyText;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final String rowItem = items.get(position);

		if (convertView == null) {
			holder = new ViewHolder();
			holder.layout = (RelativeLayout) inflater.inflate(
					R.layout.list_row_item, null);
			holder.dateText = (TextView) holder.layout
					.findViewById(R.id.rowDateLabel);
			holder.bodyText = (TextView) holder.layout
					.findViewById(R.id.rowBodyLabel);

			convertView = holder.layout;
			convertView.setTag(holder);
		}

		holder = (ViewHolder) convertView.getTag();

		String[] parts = rowItem.split(" ");

		// set spent amount
		holder.dateText.setText("Date: " + parts[0]);
		holder.bodyText.setText("Content:" + parts[parts.length-1]);

		return convertView;
	}

}
