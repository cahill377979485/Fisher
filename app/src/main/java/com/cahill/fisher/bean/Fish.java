package com.cahill.fisher.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.Val;

import java.util.List;
import java.util.Objects;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 3:47
 * @desc
 */
public class Fish implements Parcelable {
    private int id;
    private boolean selected;
    private String name;
    private List<Fish> producer;
    private int num;//已拥有
    private int type;//为sss、ss、s、其他。默认的sss是2，ss是1，s是0。
    private int priority;//优先级
    private boolean required;//是否必选

    public Fish(int id, String name, List<Fish> producer, int num, int type, int priority) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.num = num;
        this.type = type;
        this.priority = priority;
    }

    public Fish(String name, List<Fish> producer, int num, int type) {
        this.name = name;
        this.producer = producer;
        this.num = num;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id").append("=").append(id).append(";")
                .append("selected").append("=").append(selected).append(";")
                .append("name").append("=").append(name).append(";");
        if (Checker.hasList(producer)) {
            for (int i = 0; i < producer.size(); i++) {
                Fish fish = producer.get(i);
                sb.append("producer").append("=").append(fish.getName()).append(";");
            }
        }
        sb.append("num").append("=").append(num).append(";")
                .append("type").append("=").append(type == Val.TYPE_SSS ? "SSS" : type == Val.TYPE_SS ? "SS" : "S").append(";")
                .append("priority").append("=").append(priority).append(";")
                .append("required").append("=").append(required);
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeByte(this.selected ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeTypedList(this.producer);
        dest.writeInt(this.num);
        dest.writeInt(this.type);
        dest.writeInt(this.priority);
        dest.writeByte(this.required ? (byte) 1 : (byte) 0);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.selected = source.readByte() != 0;
        this.name = source.readString();
        this.producer = source.createTypedArrayList(Fish.CREATOR);
        this.num = source.readInt();
        this.type = source.readInt();
        this.priority = source.readInt();
        this.required = source.readByte() != 0;
    }

    protected Fish(Parcel in) {
        this.id = in.readInt();
        this.selected = in.readByte() != 0;
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
