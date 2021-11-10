package com.cahill.fisher.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;

import com.cahill.fisher.R;
import com.cahill.fisher.base.BaseSecondActivity;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.bean.ScheduleFish;
import com.cahill.fisher.bean.TitleBean;
import com.cahill.fisher.bean.TypeData;
import com.cahill.fisher.databinding.ActivityScheduleBinding;
import com.cahill.fisher.ui.binder.FishBinder;
import com.cahill.fisher.ui.binder.TitleBinder;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.DataUtil;
import com.cahill.fisher.util.LogUtils;
import com.cahill.fisher.util.TypeDataNames;
import com.cahill.fisher.util.Val;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/10/26 0026 上午 9:29
 * @desc 勾选必选，然后设置希望的SS鱼，生成满池的安排表。希望的SSS鱼默认按照观赏度逆序顺序。
 */
public class ScheduleActivity extends BaseSecondActivity {
    private Items items = new Items();
    private MultiTypeAdapter adapter;
    private ActivityScheduleBinding binding;

    private List<Fish> listSSSType;
    private List<Fish> listSSType;
    private List<Fish> listSType;
    private List<Fish> listAllSSSType;

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
        adapter.register(TitleBean.class, new TitleBinder());
        adapter.register(Fish.class, new FishBinder());
        GridLayoutManager manager = new GridLayoutManager(this, 6);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return items.get(position) instanceof TitleBean ? 6 : 1;
            }
        });
        binding.rv.setLayoutManager(manager);
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
        binding.tv.setOnClickListener(v -> {
            List<Fish> listScheduleFish = getScheduleFish();
            ScheduleResultActivity.start(this, new ScheduleFish(listScheduleFish));
        });
        reloadData();
    }

    /**
     * 加载数据
     */
    private void reloadData() {
        listSSSType = new ArrayList<>();
        listSSType = new ArrayList<>();
        listSType = new ArrayList<>();
        listAllSSSType = new ArrayList<>();
        listAllSSSType.add(DataUtil.getFish("处女座"));
        listAllSSSType.add(DataUtil.getFish("射手座"));
        listAllSSSType.add(DataUtil.getFish("金牛座"));
        listAllSSSType.add(DataUtil.getFish("水瓶座"));
        listAllSSSType.add(DataUtil.getFish("摩羯座"));
        listAllSSSType.add(DataUtil.getFish("巨蟹座"));
        listAllSSSType.add(DataUtil.getFish("天蝎座"));
        listAllSSSType.add(DataUtil.getFish("狮子座"));
        listAllSSSType.add(DataUtil.getFish("白羊座"));
        int sizeSSS = 0;
        int sizeSS = 0;
        int sizeS = 0;
        if (Checker.hasList(listAllSSSType)) {
            for (int i = 0; i < listAllSSSType.size(); i++) {
                Fish fish = listAllSSSType.get(i);
                List<Fish> listParent = fish.getProducer();
                if (Checker.hasList(listParent)) {
                    for (int j = 0; j < listParent.size(); j++) {
                        Fish fishParent = listParent.get(j);
                        List<Fish> listGrandParent = fishParent.getProducer();
                        if (Checker.hasList(listGrandParent)) {
                            for (int k = 0; k < listGrandParent.size(); k++) {
                                Fish fishGrandParent = listGrandParent.get(k);
                                addFish(listSSSType, listSSType, listSType, fishGrandParent);//S
                                sizeS+=fishGrandParent.getNum();
                            }
                        }
                        addFish(listSSSType, listSSType, listSType, fishParent);//SS
                        sizeSS+=fishParent.getNum();
                    }
                }
                addFish(listSSSType, listSSType, listSType, fish);//SSS
                sizeSSS+=fish.getNum();
            }
        }
        if (Checker.hasList(items)) {
            items.clear();
            adapter.notifyDataSetChanged();
        }
        //SSS
        items.add(new TitleBean("星座鱼" + "(" + listSSSType.size() + "种 共" + sizeSSS + "只)"));
        adapter.notifyDataSetChanged();
        items.addAll(listSSSType);
        adapter.notifyDataSetChanged();
        //SS
        items.add(new TitleBean("SS鱼" + "(" + listSSType.size() + "种 共" + sizeSS + "只)"));
        adapter.notifyDataSetChanged();
        items.addAll(listSSType);
        adapter.notifyDataSetChanged();
        //S
        items.add(new TitleBean("S鱼" + "(" + listSType.size() + "种 共" + sizeS + "只)"));
        adapter.notifyDataSetChanged();
        items.addAll(listSType);
        adapter.notifyDataSetChanged();
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

    /**
     * 有bug：选择SSS鱼之后，后面会重复出现SS鱼。
     *
     * @return
     */
    private List<Fish> getScheduleFish() {
        if (Checker.noList(items)) {
            Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
            return null;
        }
        List<Fish> listAllUnit = new ArrayList<>();
        int id = 0;
        if (Checker.hasList(listSSSType)) {
            for (int i = 0; i < listSSSType.size(); i++) {
                Fish fish = listSSSType.get(i);
                int num = fish.getNum();
                if (num > 0) {
                    for (int j = 0; j < num; j++) {
                        Fish f = new Fish(++id, fish.getName(), fish.getProducer(), fish.getNum(), fish.getType(), fish.getPriority());
                        f.setSelected(false);
                        listAllUnit.add(f);
                    }
                }
            }
        }
        if (Checker.hasList(listSSType)) {
            for (int i = 0; i < listSSType.size(); i++) {
                Fish fish = listSSType.get(i);
                int num = fish.getNum();
                if (num > 0) {
                    for (int j = 0; j < num; j++) {
                        Fish f = new Fish(++id, fish.getName(), fish.getProducer(), fish.getNum(), fish.getType(), fish.getPriority());
                        f.setSelected(false);
                        listAllUnit.add(f);
                    }
                }
            }
        }
        if (Checker.hasList(listSType)) {
            for (int i = 0; i < listSType.size(); i++) {
                Fish fish = listSType.get(i);
                int num = fish.getNum();
                if (num > 0) {
                    for (int j = 0; j < num; j++) {
                        Fish f = new Fish(++id, fish.getName(), fish.getProducer(), fish.getNum(), fish.getType(), fish.getPriority());
                        f.setSelected(false);
                        listAllUnit.add(f);
                    }
                }
            }
        }
        LogUtils.e("list.size=" + listAllUnit.size());
        List<Fish> resultPriorityType = new ArrayList<>();
        //先找到优先养的鱼，将其加入，然后将父鱼加入，最后加入祖鱼。之后根据类型，从SSS鱼加起，再加相应的SS鱼，为SSS鱼配等量的最少的SS鱼。比如三种SS鱼其中一种只有一条，则设置三种鱼各一只。
        //找到优先养的SS鱼或S鱼
        for (int i = 0; i < listAllUnit.size(); i++) {
            Fish fish = listAllUnit.get(i);
            if (fish.getType() == Val.TYPE_SSS) continue;
            if (fish.getPriority() > fish.getType()) {
                fish.setSelected(true);
                listAllUnit.get(i).setSelected(true);
                if (!hasType(resultPriorityType, fish))
                    resultPriorityType.add(fish);
            }
        }
        //将父鱼加入，并加入祖鱼
        if (Checker.hasList(resultPriorityType)) {
            for (int i = 0; i < resultPriorityType.size(); i++) {
                Fish fish = resultPriorityType.get(i);
                List<Fish> listParent = fish.getProducer();
                if (Checker.hasList(listParent)) {
                    //检查父鱼至少每种要加入几个
                    int pairsParent = getParentPairs(fish);
                    if (pairsParent > 0) {
                        for (int j = 0; j < listParent.size(); j++) {
                            Fish fishParent = listParent.get(j);
                            setPairsParentFishSelected(listAllUnit, fishParent, pairsParent);
                            //最少的父鱼
                            if (fishParent.getNum() == pairsParent) {
                                List<Fish> listGrandParent = fishParent.getProducer();
                                if (Checker.hasList(listGrandParent)) {//为最少的父鱼增加祖鱼
                                    int pairsGrandParent = getParentPairs(fishParent);
                                    if (pairsGrandParent > 0) {
                                        for (int k = 0; k < listGrandParent.size(); k++) {
                                            Fish fishGrandParent = listGrandParent.get(k);
                                            setPairsParentFishSelected(listAllUnit, fishGrandParent, pairsGrandParent);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        List<Fish> listResult = new ArrayList<>();
        List<Fish> listSelected = new ArrayList<>();
        List<Fish> listUnSelected = new ArrayList<>();
        for (int i = 0; i < listAllUnit.size(); i++) {
            Fish fish = listAllUnit.get(i);
            if (fish.isSelected()) {
                listSelected.add(fish);
            } else {
                listUnSelected.add(fish);
            }
        }
        for (int i = 0; i < listSelected.size(); i++) {
            Fish fish = listSelected.get(i);
            Log.e("listSelected", fish.toString());
        }
        listResult.addAll(listSelected);
        for (int i = 0; i < listUnSelected.size(); i++) {
            Fish fish = listUnSelected.get(i);
            Log.e("listUnSelected", fish.toString());
        }
        listResult.addAll(listUnSelected);
        for (int i = 0; i < listResult.size(); i++) {
            Fish fish = listResult.get(i);
            Log.e("listResult", fish.toString());
        }
        return listResult.subList(0, 40);
    }

    private void setPairsParentFishSelected(List<Fish> list, Fish targetFish, int pairs) {
        int n = 0;
        if (Checker.hasList(list)) {
            for (int i = 0; i < list.size(); i++) {
                Fish fish = list.get(i);
                if (fish.getName().equals(targetFish.getName()) && !fish.isSelected()) {
                    fish.setSelected(true);
                    ++n;
                    if (n >= pairs) break;
                }
            }
        }
    }

    private boolean hasType(List<Fish> list, Fish targetFish) {
        if (Checker.hasList(list)) {
            for (int i = 0; i < list.size(); i++) {
                Fish fish = list.get(i);
                if (fish.getName().equals(targetFish.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getFishNum(List<Fish> list) {
        int num = 0;
        if (Checker.hasList(list)) {
            for (int i = 0; i < list.size(); i++) {
                num += list.get(i).getNum();
            }
        }
        return num;
    }

    /**
     * 根据类型，将鱼加入到集合
     *
     * @param listSSS SSS鱼的集合
     * @param listSS  SS鱼的集合
     * @param listS   S鱼的集合
     * @param fish    鱼
     */
    private void addFish(List<Fish> listSSS, List<Fish> listSS, List<Fish> listS, Fish fish) {
        switch (fish.getType()) {
            case Val.TYPE_SSS:
                if (!hasType(listSSS, fish))
                    listSSS.add(fish);
                break;
            case Val.TYPE_SS:
                if (!hasType(listSS, fish))
                    listSS.add(fish);
                break;
            case Val.TYPE_S:
                if (!hasType(listS, fish))
                    listS.add(fish);
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
                        int priority = bean.getPriority();
                        if (priority == 0) priority = bean.getType();
                        int newPriority = priority == bean.getType() ? bean.getType() + Val.PRIORITIES.length : bean.getType();
                        fish.setPriority(newPriority);
                        DataUtil.saveFish(fish);
                        reloadData();
                        break;
                    }
                }
            }
        }
    }
}
