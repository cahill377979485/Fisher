package com.cahill.fisher.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.cahill.fisher.R;
import com.cahill.fisher.base.BaseSecondActivity;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.bean.ScheduleFish;
import com.cahill.fisher.bean.TypeData;
import com.cahill.fisher.databinding.ActivityScheduleBinding;
import com.cahill.fisher.ui.binder.FishBinder;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.DataUtil;
import com.cahill.fisher.util.TypeDataNames;
import com.cahill.fisher.util.Val;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/10/26 0026 上午 9:29
 * @desc 勾选必选，然后设置希望的星座鱼，生成满池的安排表
 */
public class ScheduleActivity extends BaseSecondActivity {
    private Items items = new Items();
    private MultiTypeAdapter adapter;
    private ActivityScheduleBinding binding;
    private int sizePoor1 = 30;// TODO: 2021/11/6 后期改成可设置
    private int sizePoor2 = 30;
    private int sizePoor3 = 28;

    private List<Fish> listSSS;
    private List<Fish> listSS;
    private List<Fish> listS;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, ScheduleActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding = ActivityScheduleBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setTitle(R.string.schedule);
        adapter = new MultiTypeAdapter(items);
        adapter.register(Fish.class, new FishBinder());
        binding.rv.setLayoutManager(new GridLayoutManager(this, 6));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
        binding.tv.setOnClickListener(v -> {
            List<Fish> listScheduleFish = getScheduleFish();
            ScheduleResultActivity.start(this, new ScheduleFish(listScheduleFish));
        });
        loadData();
    }

    /**
     * 加载数据
     */
    private void loadData() {
        //为了去重，用set来装结果
        Set<Fish> setSSS = new HashSet<>();
        Set<Fish> setSS = new HashSet<>();
        Set<Fish> setS = new HashSet<>();
        List<Fish> listAll = DataUtil.getAllFish();
        if (Checker.hasList(listAll)) {
            for (int i = 0; i < listAll.size(); i++) {
                Fish fish = listAll.get(i);//这里取到的可能是SSS或者SS鱼
                List<Fish> listParent = fish.getProducer();
                if (Checker.hasList(listParent)) {
                    for (int j = 0; j < listParent.size(); j++) {
                        Fish fishParent = listParent.get(j);
                        List<Fish> listGrandParent = fishParent.getProducer();
                        if (Checker.hasList(listGrandParent)) {
                            for (int k = 0; k < listGrandParent.size(); k++) {
                                Fish fishGrandParent = listGrandParent.get(k);
                                addFish(setSSS, setSS, setS, fishGrandParent);
                            }
                        }
                        addFish(setSSS, setSS, setS, fishParent);
                    }
                }
                addFish(setSSS, setSS, setS, fish);
            }
        }
        if (Checker.hasList(items)) {
            items.clear();
            adapter.notifyDataSetChanged();
        }
        items.addAll(setSSS);
        adapter.notifyDataSetChanged();
        items.addAll(setSS);
        adapter.notifyDataSetChanged();
        items.addAll(setS);
        adapter.notifyDataSetChanged();
        listSSS = new ArrayList<>(setSSS);
        listSS = new ArrayList<>(setSS);
        listS = new ArrayList<>(setS);
    }

    /**
     * 尝试将每条鱼赋值一个优先级，然后逆序排序，输出前90个即可
     *
     * @return 所求
     */
    private List<Fish> getScheduleFish2() {
        List<Fish> list = new ArrayList<>();
        List<Fish> listAll = new ArrayList<>();
        listAll.add(DataUtil.getFish("处女座"));
        listAll.add(DataUtil.getFish("射手座"));
        listAll.add(DataUtil.getFish("金牛座"));
        listAll.add(DataUtil.getFish("水瓶座"));
        listAll.add(DataUtil.getFish("摩羯座"));
        listAll.add(DataUtil.getFish("巨蟹座"));
        listAll.add(DataUtil.getFish("天蝎座"));
        listAll.add(DataUtil.getFish("狮子座"));
        listAll.add(DataUtil.getFish("白羊座"));
        for (int i = 0; i < listAll.size(); i++) {
            Fish fish = listAll.get(i);
            fish.setPriority(fish.isRequired() ? fish.getType() + Val.PRIORITIES.length : fish.getType());
            List<Fish> listParent = fish.getProducer();
            if (Checker.hasList(listParent)) {
                for (int j = 0; j < listParent.size(); j++) {
                    Fish fishParent = listParent.get(j);
                    fishParent.setPriority(fishParent.isRequired() ? fishParent.getType() + Val.PRIORITIES.length : fishParent.getType());
                    List<Fish> listGrandParent = fishParent.getProducer();
                    if (Checker.hasList(listGrandParent)) {
                        for (int k = 0; k < listGrandParent.size(); k++) {
                            Fish fishGrandParent = listGrandParent.get(k);
                            fishGrandParent.setPriority(fishGrandParent.isRequired() ? fishGrandParent.getType() + Val.PRIORITIES.length : fishGrandParent.getType());
                        }
                    }
                }
            }
        }
        //以上得到的是种类列表，所有不会有重复的鱼，需要根据每种鱼

        //按照逆序排序
        return list;
    }

    private int getParentPairs(Fish fish) {
        int pairs = 0;
        if (Checker.notNull(fish)) {
            List<Fish> listParent = fish.getProducer();
            if (Checker.hasList(listParent)) {
                pairs = 99;
                for (int i = 0; i < listParent.size(); i++) {
                    Fish f = listParent.get(i);
                    pairs = Math.min(f.getNum(), pairs);
                    if (pairs == 0) break;//只要有父鱼空缺，则直接跳出
                }
            }
        }
        return pairs;
    }

    private List<Fish> getScheduleFish() {
        List<Fish> result = new ArrayList<>();
        if (Checker.noList(items)) {
            Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
            return null;
        }
        int totalSize = sizePoor1 + sizePoor2 + sizePoor3;
        LinkedList<Fish> resultPriority = new LinkedList<>();
        LinkedList<Fish> resultPriorityParent = new LinkedList<>();
        LinkedList<Fish> resultPriorityGrandParent = new LinkedList<>();
        //先找到优先养的鱼，将其加入，然后将父鱼加入，最后加入祖鱼。之后根据类型，从SSS鱼加起，再加相应的SS鱼，为SSS鱼配等量的最少的SS鱼。比如三种SS鱼其中一种只有一条，则设置三种鱼各一只。
        //找到优先养的鱼
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            if (o instanceof Fish) {
                Fish fish = (Fish) o;
                if (fish.isRequired()) {
                    resultPriority.add(fish);
                }
            }
        }
        if (result.size() >= totalSize) return result;
        //将父鱼加入，并加入祖鱼
        if (Checker.hasList(resultPriority)) {
            for (int i = 0; i < resultPriority.size(); i++) {
                Fish fish = resultPriority.get(i);
                List<Fish> listParent = fish.getProducer();
                if (Checker.hasList(listParent)) {
                    //检查父鱼至少每种要加入几个
                    int pairs = getParentPairs(fish);
                    //已求出每种父鱼要加多少条，这里加入父鱼
                    if (pairs > 0) {
                        for (int j = 0; j < listParent.size(); j++) {
                            Fish fishParent = listParent.get(j);
                            //加入最低额的父鱼们
                            for (int k = 0; k < pairs; k++) {
                                resultPriorityParent.add(fishParent);
                            }
                            //为最少的父鱼增加祖鱼
                            if (fishParent.getNum() == pairs) {
                                List<Fish> listGrandParent = fishParent.getProducer();
                                if (Checker.hasList(listGrandParent)) {
                                    for (int k = 0; k < listGrandParent.size(); k++) {
                                        resultPriorityGrandParent.add(listGrandParent.get(k));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        result.addAll(resultPriority);
        if (result.size() >= totalSize) return result;
        result.addAll(resultPriorityParent);
        if (result.size() >= totalSize) return result;
        result.addAll(resultPriorityGrandParent);
        if (result.size() >= totalSize) return result;
        //将SSS、SS、S填满剩下的格子
        //SSS
        LinkedList<Fish> resultSSS = new LinkedList<>();
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            if (o instanceof Fish) {
                Fish fish = (Fish) o;
                if (fish.getType() == Val.TYPE_SSS) {
                    resultSSS.add(fish);
                }
            }
        }
        result.addAll(resultSSS);
        if (result.size() >= totalSize) return result;
        //SS
        LinkedList<Fish> resultSS = new LinkedList<>();
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            if (o instanceof Fish) {
                Fish fish = (Fish) o;
                if (fish.getType() == Val.TYPE_SS) {
                    resultSS.add(fish);
                }
            }
        }
        result.addAll(resultSS);
        //S
        LinkedList<Fish> resultS = new LinkedList<>();
        for (int i = 0; i < items.size(); i++) {
            Object o = items.get(i);
            if (o instanceof Fish) {
                Fish fish = (Fish) o;
                if (fish.getType() == Val.TYPE_S) {
                    resultS.add(fish);
                }
            }
        }
        result.addAll(resultS);
        return result;
    }

    /**
     * 根据类型，将鱼加入到Set中，这样可以自动去重
     *
     * @param setSSS SSS鱼的Set
     * @param setSS  SS鱼的Set
     * @param setS   S鱼的Set
     * @param fish   鱼
     */
    private void addFish(Set<Fish> setSSS, Set<Fish> setSS, Set<Fish> setS, Fish fish) {
        switch (fish.getType()) {
            case Val.TYPE_SSS:
                setSSS.add(fish);
                break;
            case Val.TYPE_SS:
                setSS.add(fish);
                break;
            case Val.TYPE_S:
                setS.add(fish);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void threadHandle(TypeData typeData) {
        String name = typeData.getName();
        if (name.equals(TypeDataNames.clickFish)) {
            Fish bean = (Fish) typeData.getData();
            if (Checker.isNull(bean)) return;
            if (Checker.noList(items)) return;
            for (int i = 0; i < items.size(); i++) {
                Object o = items.get(i);
                if (o instanceof Fish) {
                    Fish fish = (Fish) o;
                    if (fish.getName().equals(bean.getName())) {
                        int priority = bean.getPriority() == bean.getType() ? bean.getType() + Val.PRIORITIES.length : bean.getType();
                        fish.setPriority(priority);
                        DataUtil.saveFish(fish);
                        adapter.notifyDataSetChanged();
                        break;
                    }
                }
            }
        }
    }
}
