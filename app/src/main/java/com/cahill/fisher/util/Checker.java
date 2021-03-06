package com.cahill.fisher.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 文琳
 * @time 2017/8/17
 * @desc 检查类，简单封装
 */
public class Checker {
    public static boolean notNull(Object object) {
        return object != null;
    }

    public static boolean isNull(Object object) {
        return object == null;
    }

    public static boolean hasList(List list) {
        return list != null && !list.isEmpty();
    }

    public static boolean noList(List list) {
        return !hasList(list);
    }

    public static boolean hasMap(Map map) {
        return map != null && !map.isEmpty();
    }

    public static boolean hasWord(String str) {
        return str != null && !TextUtils.isEmpty(str);
    }

    public static boolean noWord(String str) {
        return !hasWord(str);
    }

    public static String formatWord(String str) {
        return hasWord(str) ? str : "";
    }

    public static boolean isPic(String picUrl) {
        return hasWord(picUrl) && picUrl.startsWith("http") && picUrl.length() > 10 && (picUrl.endsWith(".jpg") || picUrl.endsWith(".jpeg") || picUrl.endsWith(".png") || picUrl.endsWith(".gif"));
    }

    public static boolean notPic(String headUrl) {
        return !isPic(headUrl);
    }

    public static boolean isUrl(String url) {
        return hasWord(url) && url.startsWith("http") && url.length() > 10;
    }

    public static boolean notUrl(String url) {
        return !isUrl(url);
    }
}
