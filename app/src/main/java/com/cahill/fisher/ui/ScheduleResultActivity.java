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
import com.cahill.fisher.bean.TitleBean;
import com.cahill.fisher.databinding.ActivityScheduleResultBinding;
import com.cahill.fisher.ui.binder.FishBinder;
import com.cahill.fisher.ui.binder.TitleBinder;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.Val;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class ScheduleResultActivity extends BaseSecondActivity {

    private Items items = new Items();
    private MultiTypeAdapter adapter;
    private ActivityScheduleResultBinding binding;

    private ScheduleFish scheduleFish;

    public static void start(Activity activity, ScheduleFish scheduleFish) {
        Intent intent = new Intent(activity, ScheduleResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Val.DATA, scheduleFish);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        scheduleFish = getIntent().getParcelableExtra(Val.DATA);
        if (Checker.isNull(scheduleFish)) {
            Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        binding = ActivityScheduleResultBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setTitle(R.string.schedule_result);
        adapter = new MultiTypeAdapter(items);
        adapter.register(TitleBean.class, new TitleBinder());
        adapter.register(Fish.class, new FishBinder());
        GridLayoutManager manager = new GridLayoutManager(this, 6);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return items.get(position) instanceof Fish ? 1 : 6;
            }
        });
        binding.rv.setLayoutManager(manager);
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        List<Fish> listAll = scheduleFish.getList();
        if (Checker.noList(listAll)) return;
        int n = 1;
        int totalNum = 0;
        for (int i = 0; i < listAll.size(); i++) {
            Fish fish = listAll.get(i);
            int num = fish.getNum();
            for (int j = 0; j < num; j++) {
                if (totalNum % 30 == 0) {
                    items.add(new TitleBean("第" + (n++) + "池"));
                    adapter.notifyItemInserted(items.size() - 1);
                }
                totalNum++;
                items.add(listAll.get(i));
                adapter.notifyItemInserted(items.size() - 1);
            }
        }
    }
}
