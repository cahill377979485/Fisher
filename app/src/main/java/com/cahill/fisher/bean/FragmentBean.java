package com.cahill.fisher.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 3:50
 * @desc 兑换所需碎片和已有碎片
 */
public class FragmentBean implements Parcelable {
    private int need;
    private int has;

    public FragmentBean(int need, int has) {
        this.need = need;
        this.has = has;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public int getHas() {
        return has;
    }

    public void setHas(int has) {
        this.has = has;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.need);
        dest.writeInt(this.has);
    }

    public void readFromParcel(Parcel source) {
        this.need = source.readInt();
        this.has = source.readInt();
    }

    protected FragmentBean(Parcel in) {
        this.need = in.readInt();
        this.has = in.readInt();
    }

    public static final Parcelable.Creator<FragmentBean> CREATOR = new Parcelable.Creator<FragmentBean>() {
        @Override
        public FragmentBean createFromParcel(Parcel source) {
            return new FragmentBean(source);
        }

        @Override
        public FragmentBean[] newArray(int size) {
            return new FragmentBean[size];
        }
    };
}
