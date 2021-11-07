package com.cahill.fisher.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ScheduleFish implements Parcelable {
    private List<Fish> list;

    public ScheduleFish(List<Fish> list) {
        this.list = list;
    }

    public List<Fish> getList() {
        return list;
    }

    public void setList(List<Fish> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
    }

    protected ScheduleFish(Parcel in) {
        this.list = in.createTypedArrayList(Fish.CREATOR);
    }

    public static final Parcelable.Creator<ScheduleFish> CREATOR = new Parcelable.Creator<ScheduleFish>() {
        @Override
        public ScheduleFish createFromParcel(Parcel source) {
            return new ScheduleFish(source);
        }

        @Override
        public ScheduleFish[] newArray(int size) {
            return new ScheduleFish[size];
        }
    };
}
