package com.cahill.fisher.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 3:47
 * @desc
 */
public class Fish implements Parcelable {
    private String name;
    private List<Fish> producer;
    private int num;//已拥有
    private int type;//为sss、ss、s、其他。默认的sss是2，ss是1，s是0。
    private int priority;//优先级
    private boolean required;//是否必选

    public Fish(String name, List<Fish> producer, int num, int type) {
        this.name = name;
        this.producer = producer;
        this.num = num;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fish> getProducer() {
        return producer;
    }

    public void setProducer(List<Fish> producer) {
        this.producer = producer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedList(this.producer);
        dest.writeInt(this.num);
        dest.writeInt(this.type);
        dest.writeInt(this.priority);
        dest.writeByte(this.required ? (byte) 1 : (byte) 0);
    }

    protected Fish(Parcel in) {
        this.name = in.readString();
        this.producer = in.createTypedArrayList(Fish.CREATOR);
        this.num = in.readInt();
        this.type = in.readInt();
        this.priority = in.readInt();
        this.required = in.readByte() != 0;
    }

    public static final Creator<Fish> CREATOR = new Creator<Fish>() {
        @Override
        public Fish createFromParcel(Parcel source) {
            return new Fish(source);
        }

        @Override
        public Fish[] newArray(int size) {
            return new Fish[size];
        }
    };
}
