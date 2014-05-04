package edu.jmm.intentsdemo;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.jmm.R;
import edu.jmm.R.id;
import edu.jmm.R.layout;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class FetchPhotoActivity extends Activity {

	public static final int PICK_FROM_FILE = 2;
	public static final int VIEW_PHOTO = 4;
	public static final String VIEW_PHOTO_KEY = "Key";
	public static final String TAG = "FetchPhotoActivity";
	public static final String IMAGE_VIEWING_REQUESTED = "edu.jmm.action.IMAGE_VIEWING_REQUESTED";
	private static final String DATA_MIMETYPE = ContactsContract.Data.MIMETYPE;
	private static final Uri DATA_CONTENT_URI = ContactsContract.Data.CONTENT_URI;
	private static final String DATA_CONTACT_ID = ContactsContract.Data.CONTACT_ID;

	private static final String CONTACTS_ID = ContactsContract.Contacts._ID;
	private static final Uri CONTACTS_CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;

	private static final String STRUCTURED_POSTAL_CONTENT_ITEM_TYPE = ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE;
	private static final String STRUCTURED_POSTAL_FORMATTED_ADDRESS = ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS;
	private static final int PICK_CONTACT_REQUEST = 0;

	public Button fetchPhoto;
	private Button customIntent;
	public ImageView fetchedImg;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intents_and_bcast_receiver);
		
		fetchPhoto = (Button) findViewById(R.id.fetchImageButton);
		customIntent = (Button) findViewById(R.id.ViewFullScreenButton);
		fetchedImg = (ImageView) findViewById(R.id.fetchedImageView);
		
		fetchPhoto.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK,
						CONTACTS_CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT_REQUEST);
			}
		});
		
		customIntent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent grabPhoto = new Intent();
				grabPhoto.setType("image/*");
				grabPhoto.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(grabPhoto, VIEW_PHOTO);
			}
		});
		
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK
				&& requestCode == PICK_CONTACT_REQUEST) {

			ContentResolver cr = getContentResolver();
			Cursor cursor = cr.query(data.getData(), null, null, null, null);

			if (null != cursor && cursor.moveToFirst()) {
				String id = cursor
						.getString(cursor.getColumnIndex(CONTACTS_ID));
				String where = DATA_CONTACT_ID + " = ? AND " + DATA_MIMETYPE
						+ " = ?";
				String[] whereParameters = new String[] { id,
						STRUCTURED_POSTAL_CONTENT_ITEM_TYPE };
				Cursor addrCur = cr.query(DATA_CONTENT_URI, null, where,
						whereParameters, null);

				if (null != addrCur && addrCur.moveToFirst()) {
					String formattedAddress = addrCur
							.getString(addrCur
									.getColumnIndex(STRUCTURED_POSTAL_FORMATTED_ADDRESS));

					if (null != formattedAddress) {
						formattedAddress = formattedAddress.replace(' ', '+');
						Toast.makeText(this, "Imeto e:"+formattedAddress, Toast.LENGTH_LONG).show();
//						Intent geoIntent = new Intent(
//								android.content.Intent.ACTION_VIEW,
//								Uri.parse("geo:0,0?q=" + formattedAddress));
//						startActivity(geoIntent);
					}
				}
				if (null != addrCur)
					addrCur.close();
		  }
		}
		if(resultCode == RESULT_OK && requestCode==VIEW_PHOTO){

			Uri imageUri = data.getData();

			Intent custom = new Intent();
			custom.setAction(IMAGE_VIEWING_REQUESTED);
			custom.putExtra(VIEW_PHOTO_KEY, imageUri);
			startActivity(custom);

		}
		    
//			if (null != cursor)
//				cursor.close();
	}

}
