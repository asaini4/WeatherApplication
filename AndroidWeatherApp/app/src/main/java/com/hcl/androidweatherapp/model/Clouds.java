package com.hcl.androidweatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Clouds implements Parcelable{

    private  int all;


    public Clouds() {
    }

    private Clouds(Parcel in) {
        all = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(all);
    }

    @SuppressWarnings("unused")
    public static final Creator<Clouds> CREATOR = new Creator<Clouds>() {
        @Override
        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        @Override
        public Clouds[] newArray(int size) {
            return new Clouds[size];
        }
    };

}
