package com.cahill.fisher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.cahill.fisher.base.BaseActivity;
import com.cahill.fisher.bean.TagBean;
import com.cahill.fisher.binder.TagBinder;
import com.cahill.fisher.databinding.ActivityMainBinding;
import com.cahill.fisher.ui.RelationshipActivity;

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
        binding.tv.setText("主页");
        binding.rv.setLayoutManager(new GridLayoutManager(this, 1));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
        //data
        items.add(new TagBean("关系", v-> RelationshipActivity.start(this)));
        items.add(new TagBean("数量", v-> RelationshipActivity.start(this)));
        adapter.notifyDataSetChanged();
    }

}