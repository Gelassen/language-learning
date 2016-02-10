package com.home.languagelearning.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dmitry.kazakov on 2/10/2016.
 */
public class PageInfo implements Parcelable{
    private int total;

    public PageInfo(int total) {
        this.total = total;
    }

    protected PageInfo(Parcel in) {
        total = in.readInt();
    }

    public int getTotal() {
        return total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total);
    }

    public static final Creator<PageInfo> CREATOR = new Creator<PageInfo>() {
        @Override
        public PageInfo createFromParcel(Parcel in) {
            return new PageInfo(in);
        }

        @Override
        public PageInfo[] newArray(int size) {
            return new PageInfo[size];
        }
    };
}
