package com.cahill.fisher.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 4:43
 * @desc
 */
public class AllFish implements Parcelable {
    private List<Fish> list;

    public AllFish(List<Fish> list) {
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

    public void readFromParcel(Parcel source) {
        this.list = source.createTypedArrayList(Fish.CREATOR);
    }

    protected AllFish(Parcel in) {
        this.list = in.createTypedArrayList(Fish.CREATOR);
    }

    public static final Parcelable.Creator<AllFish> CREATOR = new Parcelable.Creator<AllFish>() {
        @Override
        public AllFish createFromParcel(Parcel source) {
            return new AllFish(source);
        }

        @Override
        public AllFish[] newArray(int size) {
            return new AllFish[size];
        }
    };
}
