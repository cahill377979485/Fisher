package com.cahill.fisher.util;

import com.cahill.fisher.bean.AllFish;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.bean.TypeData;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 4:32
 * @desc
 */
public class DataUtil {


    public static void initAllFish() {
        Fish yly = new Fish("银龙鱼", null, "银龙鱼石", 500, 908, false);
        Fish lsy = new Fish("蓝鲨鱼", null, "蓝鲨鱼石", 500, 936, false);
        Fish bom = new Fish("布偶猫", null, "布偶猫石", 500, 936, false);
        Fish xs = new Fish("西施", null, "西施石", 500, 936, false);
        Fish lrf = new Fish("卤肉饭", null, "卤肉饭石", 500, 936, false);
        Fish ddy = new Fish("倒吊鱼", null, "倒吊鱼石", 500, 936, false);
        Fish fxyy = new Fish("反向游鱼", null, "反向游鱼石", 500, 936, false);
        Fish jpy = new Fish("键盘鱼", null, "键盘石", 500, 936, false);
        Fish tyj = new Fish("团圆饺", null, "团圆饺石", 500, 936, false);
        Fish tgy = new Fish("铜管鱼", null, "铜管石", 500, 936, false);
        Fish tqy = new Fish("提琴鱼", null, "提琴石", 500, 936, false);
        Fish jfm = new Fish("加菲猫", null, "加菲猫石", 500, 936, false);
        Fish cwy = new Fish("刺尾鱼", null, "刺尾鱼石", 500, 936, false);
        Fish cts = new Fish("锤头鲨", null, "锤头鲨石", 500, 936, false);
        Fish dgy = new Fish("蛋糕鱼", null, "蛋糕石", 1000, 1038, false);
        Fish bx = new Fish("比熊", null, "比熊石", 1000, 705, false);
        Fish pf = new Fish("泡芙", null, "泡芙石", 1000, 1929, false);
        //粉虎鲸
        List<Fish> listFHJ = new ArrayList<>();
        listFHJ.add(yly);
        listFHJ.add(lsy);
        Fish fhj = new Fish("粉虎鲸", listFHJ, "粉虎鲸石", 1000, 612, false);
        DataUtil.saveFish(fhj);
        //孟加拉猫
        List<Fish> listMJLM = new ArrayList<>();
        listMJLM.add(yly);
        listMJLM.add(bom);
        Fish mjlm = new Fish("孟加拉猫", listMJLM, "孟加拉猫石", 1000, 865, false);
        DataUtil.saveFish(mjlm);
        //斑点狗
        List<Fish> listBDG = new ArrayList<>();
        listBDG.add(lsy);
        listBDG.add(xs);
        Fish bdg = new Fish("斑点狗", listBDG, "斑点狗石", 1000, 292, false);
        DataUtil.saveFish(bdg);
        //白羊座
        List<Fish> listBYZ = new ArrayList<>();
        listBYZ.add(fhj);
        listBYZ.add(mjlm);
        listBYZ.add(bdg);
        Fish baiYangZuo = new Fish("白羊座", listBYZ, "白羊座石", 0, 0, false);
        DataUtil.saveFish(baiYangZuo);
        //英短
        List<Fish> listYD = new ArrayList<>();
        listYD.add(bom);
        listYD.add(jpy);
        Fish yd = new Fish("英短", listYD, "英短石", 1000, 612, false);
        DataUtil.saveFish(yd);
        //哈士奇
        List<Fish> listHSQ = new ArrayList<>();
        listHSQ.add(xs);
        listHSQ.add(lrf);
        Fish hsq = new Fish("哈士奇", listHSQ, "哈士奇石", 1000, 865, false);
        DataUtil.saveFish(hsq);
        //帝王虾
        List<Fish> listDWX = new ArrayList<>();
        listDWX.add(fxyy);
        listDWX.add(ddy);
        Fish dwx = new Fish("帝王虾", listDWX, "帝王虾石", 1000, 292, false);
        DataUtil.saveFish(dwx);
        //狮子座
        List<Fish> listSZZ = new ArrayList<>();
        listSZZ.add(yd);
        listSZZ.add(hsq);
        listSZZ.add(dwx);
        Fish shiZiZuo = new Fish("狮子座", listSZZ, "狮子座石", 0, 0, false);
        DataUtil.saveFish(shiZiZuo);
        //火锅鱼
        Fish hgy = new Fish("火锅鱼", null, "火锅石", 1000, 612, false);
        DataUtil.saveFish(hgy);
        //斯芬克斯猫
        List<Fish> listSFKSM = new ArrayList<>();
        listSFKSM.add(bom);
        listSFKSM.add(cwy);
        Fish sfksm = new Fish("斯芬克斯猫", listSFKSM, "斯芬克斯猫石", 1000, 865, false);
        DataUtil.saveFish(sfksm);
        //古牧
        List<Fish> listGM = new ArrayList<>();
        listGM.add(xs);
        listGM.add(cts);
        Fish gm = new Fish("古牧", listGM, "古牧石", 1000, 292, false);
        DataUtil.saveFish(gm);
        //天蝎座
        List<Fish> listTXZ = new ArrayList<>();
        listTXZ.add(hgy);
        listTXZ.add(sfksm);
        listTXZ.add(gm);
        Fish tianXieZuo = new Fish("天蝎座", listTXZ, "天蝎座石", 0, 0, false);
        DataUtil.saveFish(tianXieZuo);
        //拨弦鱼
        List<Fish> listBXY = new ArrayList<>();
        listBXY.add(tqy);
        listBXY.add(tgy);
        Fish bxy = new Fish("拨弦鱼", listBXY, "拨弦鱼石", 1000, 612, false);
        DataUtil.saveFish(bxy);
        //石斑鱼
        List<Fish> listSBY = new ArrayList<>();
        listSBY.add(jfm);
        listSBY.add(tyj);
        Fish sby = new Fish("石斑鱼", listSBY, "石斑鱼石", 1000, 865, false);
        DataUtil.saveFish(sby);
        //招财猫
        Fish zcm = new Fish("招财猫", null, "招财猫石", 1000, 292, false);
        DataUtil.saveFish(dwx);
        //巨蟹座
        List<Fish> listJXZ = new ArrayList<>();
        listJXZ.add(bxy);
        listJXZ.add(sby);
        listJXZ.add(zcm);
        Fish juXieZuo = new Fish("巨蟹座", listJXZ, "巨蟹座石", 0, 0, false);
        DataUtil.saveFish(juXieZuo);
        //蛋糕鱼、比熊、泡芙
        DataUtil.saveFish(dgy);
        DataUtil.saveFish(bx);
        DataUtil.saveFish(pf);
        //水瓶座
        List<Fish> listSPZ = new ArrayList<>();
        listSPZ.add(dgy);
        listSPZ.add(bx);
        listSPZ.add(pf);
        Fish shuiPingZuo = new Fish("水瓶座", listSPZ, "水瓶座石", 0, 0, false);
        DataUtil.saveFish(shuiPingZuo);
    }

    public static void saveFish(Fish fish) {
        List<Fish> list = getAllFish();
        boolean has = false;
        if (Checker.hasList(list)) {
            for (int i = 0; i < list.size(); i++) {
                Fish f = list.get(i);
                if (f.getName().equals(fish.getName())) {
                    list.set(i, fish);
                    if (!has) has = true;
                }
                List<Fish> listParent = f.getProducer();
                if (Checker.hasList(listParent)) {
                    for (int j = 0; j < listParent.size(); j++) {
                        Fish fishParent = listParent.get(j);
                        if (fishParent.getName().equals(fish.getName())) {
                            listParent.set(j, fish);
                            if (!has) has = true;
                        }
                        List<Fish> listGrandParent = fishParent.getProducer();
                        if (Checker.hasList(listGrandParent)) {
                            for (int k = 0; k < listGrandParent.size(); k++) {
                                Fish fishGrandParent = listGrandParent.get(k);
                                if (fishGrandParent.getName().equals(fish.getName())) {
                                    listGrandParent.set(k, fish);
                                    if (!has) has = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!has) list.add(fish);
        saveAllFish(list);
    }

    public static void saveAllFish(List<Fish> list) {
        String str = new Gson().toJson(new AllFish(list), AllFish.class);
        SPUtils.getInstance().put(ValSp.ALL_FISH, str);
        EventBus.getDefault().post(new TypeData<>(TypeDataNames.updateAllFish, true));
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
