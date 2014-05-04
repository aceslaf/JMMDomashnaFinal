package edu.jmm.intentsdemo;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.jmm.R;
import edu.jmm.R.id;
import edu.jmm.R.layout;
import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

public class ViewImageActivity extends Activity {

	public static final String TAG = "ViewImageActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_image);

		ImageView rView = (ImageView) findViewById(R.id.fullImgView);
		Uri imgId = (Uri)getIntent().getExtras().get(FetchPhotoActivity.VIEW_PHOTO_KEY);
		rView.setImageBitmap(loadImage(imgId));
	}
	
	
	private Bitmap loadImage(Uri imageUri){
		Bitmap photo = null;
		try {
			photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return photo;
	}
	
}
