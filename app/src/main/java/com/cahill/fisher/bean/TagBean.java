package com.cahill.fisher.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 3:55
 * @desc
 */
public class TagBean implements Parcelable {
    private String name;
    private View.OnClickListener listener;

    public TagBean(String name, View.OnClickListener listener) {
        this.name = name;
        this.listener = listener;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.listener = source.readParcelable(View.OnClickListener.class.getClassLoader());
    }

    protected TagBean(Parcel in) {
        this.name = in.readString();
        this.listener = in.readParcelable(View.OnClickListener.class.getClassLoader());
    }

    public static final Parcelable.Creator<TagBean> CREATOR = new Parcelable.Creator<TagBean>() {
        @Override
        public TagBean createFromParcel(Parcel source) {
            return new TagBean(source);
        }

        @Override
        public TagBean[] newArray(int size) {
            return new TagBean[size];
        }
    };
}
