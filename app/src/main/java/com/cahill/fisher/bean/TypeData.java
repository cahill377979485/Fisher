package com.cahill.fisher.bean;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 3:59
 * @desc
 */
public class TypeData<T> {
    private String name;
    private T data;

    public TypeData(String name, T data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
