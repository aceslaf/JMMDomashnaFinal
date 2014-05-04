package edu.jmm.sqlservicedemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import edu.jmm.R;

public class ListAdapter extends ArrayAdapter<Model> {

	private final Context context;
	private List<Model> items;
	private LayoutInflater inflater;
	
	public ListAdapter(Context context, List<Model> resource) {
		super(context, R.layout.activity_display_items, resource);

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		items = resource;
		this.context = context;
	}
	
	class ViewHolder {
		LinearLayout layout;
		TextView titleText;
		TextView linkText;
		TextView idText;
	}	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final Model rowItem = items.get(position);
		
		if(convertView == null){
			holder = new ViewHolder();
			holder.layout= (LinearLayout) inflater.inflate(R.layout.row_item, null);
			
			holder.idText = (TextView) holder.layout.findViewById(R.id.itemId);
			holder.titleText = (TextView) holder.layout.findViewById(R.id.itemTitle);
			holder.linkText = (TextView) holder.layout.findViewById(R.id.itemLink);
			
			convertView = holder.layout;
			convertView.setTag(holder);
		
		}
		
		holder = (ViewHolder) convertView.getTag();
		
		holder.idText.setText(Integer.toString(rowItem.getId()));
		holder.titleText.setText(rowItem.getTitle());
		
		
		return convertView;
	}

	

}
