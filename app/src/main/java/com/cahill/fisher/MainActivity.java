package com.cahill.fisher;

import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.cahill.fisher.base.BaseActivity;
import com.cahill.fisher.bean.TagBean;
import com.cahill.fisher.ui.binder.TagBinder;
import com.cahill.fisher.databinding.ActivityMainBinding;
import com.cahill.fisher.ui.RelationshipActivity;
import com.cahill.fisher.ui.ScheduleActivity;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.DataUtil;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class MainActivity extends BaseActivity {

    private Items items = new Items();
    private MultiTypeAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void init(Bundle savedInstanceState) {
        adapter = new MultiTypeAdapter(items);
        adapter.register(TagBean.class, new TagBinder());
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.rv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
        if (Checker.noList(DataUtil.getAllFish())) {
            DataUtil.initAllFish();
        }
        //data
        items.add(new TagBean("关系", v -> RelationshipActivity.start(this)));
        items.add(new TagBean("安排", v -> ScheduleActivity.start(this)));
        adapter.notifyDataSetChanged();
    }

}