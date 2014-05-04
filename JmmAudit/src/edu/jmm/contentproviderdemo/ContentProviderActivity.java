package edu.jmm.contentproviderdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import edu.jmm.R;

public class ContentProviderActivity extends ListActivity {

	public static final String TAG = "ContentProviderActivity";

	private Spinner telNumChooser;
	private List<String> numbers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.content_provider_main);

		telNumChooser = (Spinner) findViewById(R.id.telNumberSpinner);
		numbers = new LinkedList<String>();

		getNumbers();

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, numbers);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		telNumChooser.setAdapter(dataAdapter);

		

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		String m = (String) getListView().getItemAtPosition(position);
		Toast.makeText(this, m.toString(), Toast.LENGTH_SHORT).show();

	}

	private void getNumbers() {

		Cursor phones = getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,null, null);

		int nameColumn = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
		int numberColumn = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

		boolean iterate = false;
		if (phones.getCount() > 0)
			iterate = true;

		phones.moveToFirst();
		while (iterate) {
			String name = phones.getString(nameColumn);
			String phoneNumber = phones.getString(numberColumn);
			numbers.add(name + ": " + phoneNumber);
			if (!phones.moveToNext())
				iterate = false;

		}

		phones.close();

	}

	private List<String> getMessages(String num) {
		List<String> messages = new LinkedList<String>();

		Cursor sms = getContentResolver().query(Uri.parse("content://sms"),
				new String[] { "_id", "thread_id", "address", "date", "body" },
				null, null, null);

		sms.moveToFirst();
		int smsBody = sms.getColumnIndex("body");
		int smsDate = sms.getColumnIndex("date");
		int smsAdress = sms.getColumnIndex("address");
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd,yyyy");

		boolean iterate = false;
		if (sms.getCount() > 0)
			iterate = true;

		while (iterate) {

			String adr = sms.getString(smsAdress);

			if (num.equalsIgnoreCase(adr)) {

				String msg = sms.getString(smsBody);
				String date = sms.getString(smsDate);
				Date resultdate = new Date(Long.parseLong(date));
				messages.add(sdf.format(resultdate) + ": " + msg);
			}

			if (!sms.moveToNext())
				iterate = false;
		}

		sms.close();

		return messages;

	}

}
