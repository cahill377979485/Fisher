package com.cahill.fisher.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.GridLayoutManager;

import com.cahill.fisher.R;
import com.cahill.fisher.base.BaseSecondActivity;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.databinding.ActivityRelationshipBinding;
import com.cahill.fisher.ui.binder.FishBinder;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 4:21
 * @desc
 */
public class RelationshipActivity extends BaseSecondActivity {

    private Items items = new Items();
    private MultiTypeAdapter adapter;
    private ActivityRelationshipBinding binding;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, RelationshipActivity.class));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding = ActivityRelationshipBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setTitle(R.string.relationship);
        setMore(v -> {
        });
        adapter = new MultiTypeAdapter(items);
        adapter.register(Fish.class, new FishBinder());
        binding.rv.setLayoutManager(new GridLayoutManager(this, 1));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
//        if (Checker.noList(DataUtil.getAllFish())) {
            DataUtil.initAllFish();
//        }
        List<Fish> list = new ArrayList<>();
        list.add(DataUtil.getFish("白羊座"));
        list.add(DataUtil.getFish("狮子座"));
        list.add(DataUtil.getFish("天蝎座"));
        list.add(DataUtil.getFish("巨蟹座"));
        list.add(DataUtil.getFish("水瓶座"));
        items.addAll(list);
        adapter.notifyDataSetChanged();
    }
}