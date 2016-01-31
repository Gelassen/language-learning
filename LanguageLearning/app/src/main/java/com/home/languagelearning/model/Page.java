package com.home.languagelearning.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dmitry.kazakov on 1/31/2016.
 */
public class Page implements Parcelable {
    private int total;
    private int current;

    public Page() {
    }

    protected Page(Parcel in) {
        total = in.readInt();
        current = in.readInt();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total);
        dest.writeInt(current);
    }

    public static final Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel in) {
            return new Page(in);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };
}
