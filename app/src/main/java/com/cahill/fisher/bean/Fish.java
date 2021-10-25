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
    private String product;
    private int need;//兑换所需
    private int has;//已有碎片
    private boolean canMutate;//是否可变异

    public Fish(String name, List<Fish> producer, String product, int need, int has, boolean canMutate) {
        this.name = name;
        this.producer = producer;
        this.product = product;
        this.need = need;
        this.has = has;
        this.canMutate = canMutate;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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

    public boolean isCanMutate() {
        return canMutate;
    }

    public void setCanMutate(boolean canMutate) {
        this.canMutate = canMutate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedList(this.producer);
        dest.writeString(this.product);
        dest.writeInt(this.need);
        dest.writeInt(this.has);
        dest.writeByte(this.canMutate ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.name = source.readString();
        this.producer = source.createTypedArrayList(Fish.CREATOR);
        this.product = source.readString();
        this.need = source.readInt();
        this.has = source.readInt();
        this.canMutate = source.readByte() != 0;
    }

    protected Fish(Parcel in) {
        this.name = in.readString();
        this.producer = in.createTypedArrayList(Fish.CREATOR);
        this.product = in.readString();
        this.need = in.readInt();
        this.has = in.readInt();
        this.canMutate = in.readByte() != 0;
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
