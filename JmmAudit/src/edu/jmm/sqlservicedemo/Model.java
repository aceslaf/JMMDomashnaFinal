package edu.jmm.sqlservicedemo;

import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {

	private String title;

	private int id;
	
	public Model() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);		
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(o.getClass() != this.getClass())
			return false;
		Model cmpModel = (Model) o;
		return id == cmpModel.getId();
	}

	@Override
	public String toString() {
		return String.format(Locale.US, "%d %s", id,title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}

}
