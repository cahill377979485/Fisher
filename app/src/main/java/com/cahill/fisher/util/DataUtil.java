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

    /**
     * 第一次加载时保存所有鱼到本地
     */
    public static void initAllFish() {
        Fish yly = new Fish("银龙鱼", null, 2, Val.TYPE_S);
        DataUtil.saveFish(yly);
        Fish lsy = new Fish("蓝鲨鱼", null, 2, Val.TYPE_S);
        DataUtil.saveFish(lsy);
        Fish bom = new Fish("布偶猫", null, 4, Val.TYPE_S);
        DataUtil.saveFish(bom);
        Fish xs = new Fish("西施", null, 6, Val.TYPE_S);
        DataUtil.saveFish(xs);
        Fish lrf = new Fish("卤肉饭", null, 6, Val.TYPE_S);
        DataUtil.saveFish(lrf);
        Fish ddy = new Fish("倒吊鱼", null, 6, Val.TYPE_S);
        DataUtil.saveFish(ddy);
        Fish fxyy = new Fish("反向游鱼", null, 6, Val.TYPE_S);
        DataUtil.saveFish(fxyy);
        Fish jpy = new Fish("键盘鱼", null, 3, Val.TYPE_S);
        DataUtil.saveFish(jpy);
        Fish tyj = new Fish("团圆饺", null, 3, Val.TYPE_S);
        DataUtil.saveFish(tyj);
        Fish tgy = new Fish("铜管鱼", null, 3, Val.TYPE_S);
        DataUtil.saveFish(tgy);
        Fish tqy = new Fish("提琴鱼", null, 5, Val.TYPE_S);
        DataUtil.saveFish(tqy);
        Fish jfm = new Fish("加菲猫", null, 5, Val.TYPE_S);
        DataUtil.saveFish(jfm);
        Fish cwy = new Fish("刺尾鱼", null, 1, Val.TYPE_S);
        DataUtil.saveFish(cwy);
        Fish cts = new Fish("锤头鲨", null, 5, Val.TYPE_S);
        DataUtil.saveFish(cts);
        Fish ysg = new Fish("鸭三姑", null, 2, Val.TYPE_S);
        DataUtil.saveFish(ysg);
        Fish pqhx = new Fish("胖球海象", null, 6, Val.TYPE_S);
        DataUtil.saveFish(pqhx);
        Fish dyjt = new Fish("电音吉他", null, 1, Val.TYPE_S);
        DataUtil.saveFish(dyjt);
        Fish dhh = new Fish("蛋黄黄", null, 6, Val.TYPE_S);
        DataUtil.saveFish(dhh);
        Fish cmgd = new Fish("草莓果冻", null, 4, Val.TYPE_S);
        DataUtil.saveFish(cmgd);
        Fish smy = new Fish("萨摩耶", null, 11, Val.TYPE_S);
        DataUtil.saveFish(smy);
        Fish zcts = new Fish("紫锤头鲨", null, 2, Val.TYPE_S);
        DataUtil.saveFish(zcts);
        Fish jly = new Fish("金龙鱼", null, 4, Val.TYPE_S);
        DataUtil.saveFish(jly);
        Fish eyyqy = new Fish("鳄鱼泳圈鱼", null, 2, Val.TYPE_S);
        DataUtil.saveFish(eyyqy);
        Fish ahmgy = new Fish("暗黑魔鬼鱼", null, 4, Val.TYPE_S);
        DataUtil.saveFish(ahmgy);
        //粉虎鲸
        List<Fish> listFHJ = new ArrayList<>();
        listFHJ.add(yly);
        listFHJ.add(lsy);
        Fish fhj = new Fish("粉虎鲸", listFHJ, 5, Val.TYPE_SS);
        DataUtil.saveFish(fhj);
        //孟加拉猫
        List<Fish> listMJLM = new ArrayList<>();
        listMJLM.add(yly);
        listMJLM.add(bom);
        Fish mjlm = new Fish("孟加拉猫", listMJLM, 4, Val.TYPE_SS);
        DataUtil.saveFish(mjlm);
        //斑点狗
        List<Fish> listBDG = new ArrayList<>();
        listBDG.add(lsy);
        listBDG.add(xs);
        Fish bdg = new Fish("斑点狗", listBDG, 3, Val.TYPE_SS);
        DataUtil.saveFish(bdg);
        //白羊座
        List<Fish> listBYZ = new ArrayList<>();
        listBYZ.add(fhj);
        listBYZ.add(mjlm);
        listBYZ.add(bdg);
        Fish baiYangZuo = new Fish("白羊座", listBYZ, 1, Val.TYPE_SSS);
        DataUtil.saveFish(baiYangZuo);
        //英短
        List<Fish> listYD = new ArrayList<>();
        listYD.add(bom);
        listYD.add(jpy);
        Fish yd = new Fish("英短", listYD, 12, Val.TYPE_SS);
        DataUtil.saveFish(yd);
        //哈士奇
        List<Fish> listHSQ = new ArrayList<>();
        listHSQ.add(xs);
        listHSQ.add(lrf);
        Fish hsq = new Fish("哈士奇", listHSQ, 9, Val.TYPE_SS);
        DataUtil.saveFish(hsq);
        //帝王虾
        List<Fish> listDWX = new ArrayList<>();
        listDWX.add(fxyy);
        listDWX.add(ddy);
        Fish dwx = new Fish("帝王虾", listDWX, 11, Val.TYPE_SS);
        DataUtil.saveFish(dwx);
        //狮子座
        List<Fish> listSZZ = new ArrayList<>();
        listSZZ.add(yd);
        listSZZ.add(hsq);
        listSZZ.add(dwx);
        Fish shiZiZuo = new Fish("狮子座", listSZZ, 5, Val.TYPE_SSS);
        DataUtil.saveFish(shiZiZuo);
        //火锅鱼
        Fish hgy = new Fish("火锅鱼", null, 5, Val.TYPE_SS);
        DataUtil.saveFish(hgy);
        //斯芬克斯猫
        List<Fish> listSFKSM = new ArrayList<>();
        listSFKSM.add(bom);
        listSFKSM.add(cwy);
        Fish sfksm = new Fish("斯芬克斯猫", listSFKSM, 5, Val.TYPE_SS);
        DataUtil.saveFish(sfksm);
        //古牧
        List<Fish> listGM = new ArrayList<>();
        listGM.add(xs);
        listGM.add(cts);
        Fish gm = new Fish("古牧", listGM, 5, Val.TYPE_SS);
        DataUtil.saveFish(gm);
        //天蝎座
        List<Fish> listTXZ = new ArrayList<>();
        listTXZ.add(hgy);
        listTXZ.add(sfksm);
        listTXZ.add(gm);
        Fish tianXieZuo = new Fish("天蝎座", listTXZ, 3, Val.TYPE_SSS);
        DataUtil.saveFish(tianXieZuo);
        //拨弦鱼
        List<Fish> listBXY = new ArrayList<>();
        listBXY.add(tqy);
        listBXY.add(tgy);
        Fish bxy = new Fish("拨弦鱼", listBXY, 4, Val.TYPE_SS);
        DataUtil.saveFish(bxy);
        //石斑鱼
        List<Fish> listSBY = new ArrayList<>();
        listSBY.add(jfm);
        listSBY.add(tyj);
        Fish sby = new Fish("石斑鱼", listSBY, 5, Val.TYPE_SS);
        DataUtil.saveFish(sby);
        //招财猫
        Fish zcm = new Fish("招财猫", null, 1, Val.TYPE_SS);
        DataUtil.saveFish(dwx);
        //巨蟹座
        List<Fish> listJXZ = new ArrayList<>();
        listJXZ.add(bxy);
        listJXZ.add(sby);
        listJXZ.add(zcm);
        Fish juXieZuo = new Fish("巨蟹座", listJXZ, 1, Val.TYPE_SSS);
        DataUtil.saveFish(juXieZuo);
        //极速中华鲟
        List<Fish> listJSZHX = new ArrayList<>();
        listJSZHX.add(ysg);
        listJSZHX.add(pqhx);
        Fish jszhx = new Fish("极速中华鲟", listJSZHX, 0, Val.TYPE_SS);
        DataUtil.saveFish(jszhx);
        //长尾斗鱼
        Fish cwdy = new Fish("长尾斗鱼", null, 1, Val.TYPE_SS);
        DataUtil.saveFish(cwdy);
        //火狐
        List<Fish> listHH = new ArrayList<>();
        listHH.add(dyjt);
        listHH.add(dhh);
        Fish hh = new Fish("火狐", listHH, 1, Val.TYPE_SS);
        DataUtil.saveFish(hh);
        //摩羯座
        List<Fish> listMJZ = new ArrayList<>();
        listMJZ.add(jszhx);
        listMJZ.add(cwdy);
        listMJZ.add(hh);
        Fish moJieZuo = new Fish("摩羯座", listMJZ, 0, Val.TYPE_SSS);
        DataUtil.saveFish(moJieZuo);
        //蛋糕鱼、比熊、泡芙
        Fish dgy = new Fish("蛋糕鱼", null, 3, Val.TYPE_SS);
        DataUtil.saveFish(dgy);
        Fish bx = new Fish("比熊", null, 2, Val.TYPE_SS);
        DataUtil.saveFish(bx);
        Fish pf = new Fish("泡芙", null, 4, Val.TYPE_SS);
        DataUtil.saveFish(pf);
        //水瓶座
        List<Fish> listSPZ = new ArrayList<>();
        listSPZ.add(dgy);
        listSPZ.add(bx);
        listSPZ.add(pf);
        Fish shuiPingZuo = new Fish("水瓶座", listSPZ, 1, Val.TYPE_SSS);
        DataUtil.saveFish(shuiPingZuo);
        //焦糖果冻
        Fish jtgd = new Fish("焦糖果冻", null, 1, Val.TYPE_SS);
        DataUtil.saveFish(jtgd);
        //墨竹鱼
        Fish mzy = new Fish("墨竹鱼", null, 3, Val.TYPE_SS);
        DataUtil.saveFish(mzy);
        //樱花和果子
        List<Fish> listYHHGZ = new ArrayList<>();
        listYHHGZ.add(cmgd);
        listYHHGZ.add(smy);
        Fish yhhgz = new Fish("樱花和果子", listYHHGZ, 2, Val.TYPE_SS);
        DataUtil.saveFish(yhhgz);
        //金牛座
        List<Fish> listJNZ = new ArrayList<>();
        listJNZ.add(jtgd);
        listJNZ.add(mzy);
        listJNZ.add(yhhgz);
        Fish jinNiuZuo = new Fish("金牛座", listJNZ, 2, Val.TYPE_SSS);
        DataUtil.saveFish(jinNiuZuo);
        //贵族驼
        Fish gzt = new Fish("贵族驼", null, 0, Val.TYPE_SS);
        DataUtil.saveFish(gzt);
        //甜蜜蜜
        Fish tmm = new Fish("甜蜜蜜", null, 0, Val.TYPE_SS);
        DataUtil.saveFish(tmm);
        //迎春花
        Fish ych = new Fish("迎春花", null, 1, Val.TYPE_SS);
        DataUtil.saveFish(ych);
        //天秤座
        List<Fish> listTCZ = new ArrayList<>();
        listTCZ.add(gzt);
        listTCZ.add(tmm);
        listTCZ.add(ych);
        Fish tianChengZuo = new Fish("天秤座", listTCZ, 0, Val.TYPE_SSS);
        DataUtil.saveFish(tianChengZuo);
        //香芋马卡龙
        Fish xymkl = new Fish("香芋马卡龙", null, 0, Val.TYPE_SS);
        DataUtil.saveFish(xymkl);
        //翡翠玉麒麟
        List<Fish> listFCYQL = new ArrayList<>();
        listFCYQL.add(zcts);
        listFCYQL.add(jly);
        Fish fcyql = new Fish("翡翠玉麒麟", listFCYQL, 1, Val.TYPE_SS);
        DataUtil.saveFish(fcyql);
        //圣诞麋鹿
        List<Fish> listSDML = new ArrayList<>();
        listSDML.add(eyyqy);
        listSDML.add(ahmgy);
        Fish sdml = new Fish("圣诞麋鹿", listSDML, 1, Val.TYPE_SS);
        DataUtil.saveFish(sdml);
        //射手座
        List<Fish> listSSZ = new ArrayList<>();
        listSSZ.add(xymkl);
        listSSZ.add(fcyql);
        listSSZ.add(sdml);
        Fish sheShouZuo = new Fish("射手座", listSSZ, 0, Val.TYPE_SSS);
        DataUtil.saveFish(sheShouZuo);
        Fish chuNvZuo = new Fish("处女座", null, 3, Val.TYPE_SSS);
        DataUtil.saveFish(chuNvZuo);
        checkPriority();
    }

    public static void checkPriority() {
        List<Fish> list = getAllFish();
        if (Checker.hasList(list)) {
            for (int i = 0; i < list.size(); i++) {
                Fish fish = list.get(i);
                if (fish.getPriority() < fish.getType()) {
                    list.get(i).setPriority(fish.getType());
                }
            }
        }
        DataUtil.saveAllFish(list);
    }

    /**
     * 保存一种鱼，如果本地已存在则覆盖，如果没有则添加
     *
     * @param fish 鱼
     */
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

    /**
     * 保存所有鱼的集合到本地
     *
     * @param list 鱼的集合
     */
    public static void saveAllFish(List<Fish> list) {
        String str = new Gson().toJson(new AllFish(list), AllFish.class);
        SPUtils.getInstance().put(ValSp.ALL_FISH, str);
        EventBus.getDefault().post(new TypeData<>(TypeDataNames.updateAllFish, true));
    }

    /**
     * 获取所有的鱼。这里指的是保存时的SSS和SS鱼，S鱼只是在SS鱼的productor里面
     *
     * @return 所求
     */
    public static List<Fish> getAllFish() {
        List<Fish> list = new ArrayList<>();
        String str = SPUtils.getInstance().getString(ValSp.ALL_FISH);
        if (Checker.hasWord(str)) list = new Gson().fromJson(str, AllFish.class).getList();
        return list;
    }

    /**
     * 根据名字获取某一种鱼
     *
     * @param fishName 鱼的名字
     * @return 所求
     */
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
