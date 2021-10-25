package com.cahill.fisher.util;

import com.cahill.fisher.bean.AllFish;
import com.cahill.fisher.bean.Fish;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 4:32
 * @desc
 */
public class DataUtil {

    public static void saveFish(Fish fish) {
        List<Fish> list = getAllFish();
        boolean has = false;
        if (Checker.hasList(list)) {
            for (int i = 0; i < list.size(); i++) {
                Fish f = list.get(i);
                if (f.getName().equals(fish.getName())) {
                    list.set(i, fish);
                    has = true;
                    break;
                }
            }
        }
        if (!has) list.add(fish);
        saveAllFish(list);
    }

    public static void saveAllFish(List<Fish> list) {
        String str = new Gson().toJson(new AllFish(list), AllFish.class);
        SPUtils.getInstance().put(ValSp.ALL_FISH, str);
    }

    public static List<Fish> getAllFish() {
        List<Fish> list = new ArrayList<>();
        String str = SPUtils.getInstance().getString(ValSp.ALL_FISH);
        if (Checker.hasWord(str)) list = new Gson().fromJson(str, AllFish.class).getList();
        return list;
    }

    public static Fish getFish(String fishName) {
        Fish fish = null;
        List<Fish> list = getAllFish();
        if (Checker.hasList(list)) {
            for (Fish f :
                    list) {
                if (f.getName().equals(fishName)) {
                    fish = f;
                    break;
                }
            }
        }
        return fish;
    }


}
