package com.cahill.fisher.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TitleBean implements Parcelable {
    private String title;

    public TitleBean(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    protected TitleBean(Parcel in) {
        this.title = in.readString();
    }

    public static final Parcelable.Creator<TitleBean> CREATOR = new Parcelable.Creator<TitleBean>() {
        @Override
        public TitleBean createFromParcel(Parcel source) {
            return new TitleBean(source);
        }

        @Override
        public TitleBean[] newArray(int size) {
            return new TitleBean[size];
        }
    };
}
