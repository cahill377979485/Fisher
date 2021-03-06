package com.cahill.fisher.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.cahill.fisher.R;
import com.cahill.fisher.base.BaseSecondActivity;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.bean.TypeData;
import com.cahill.fisher.databinding.ActivityRelationshipBinding;
import com.cahill.fisher.ui.binder.FishRelationshipBinder;
import com.cahill.fisher.ui.dialog.ManageDialog;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.DataUtil;
import com.cahill.fisher.util.TypeDataNames;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author ζη³_377979485@qq.com
 * @time 2021/9/30 0030 δΈε 4:21
 * @desc
 */
public class RelationshipActivity extends BaseSecondActivity {

    private Items items = new Items();
    private MultiTypeAdapter adapter;
    private ActivityRelationshipBinding binding;
    private ManageDialog dialog;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, RelationshipActivity.class));
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        binding = ActivityRelationshipBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setTitle(R.string.relationship);
        adapter = new MultiTypeAdapter(items);
        adapter.register(Fish.class, new FishRelationshipBinder());
        binding.rv.setLayoutManager(new GridLayoutManager(this, 1));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(binding.rv);
        loadData();
    }

    /**
     * ε θ½½ζ°ζ?
     */
    private void loadData() {
        List<Fish> list = new ArrayList<>();
        list.add(DataUtil.getFish("η½ηΎεΊ§"));
        list.add(DataUtil.getFish("η?ε­εΊ§"));
        list.add(DataUtil.getFish("ε€©θεΊ§"));
        list.add(DataUtil.getFish("ε·¨θΉεΊ§"));
        list.add(DataUtil.getFish("ζ©ηΎ―εΊ§"));
        list.add(DataUtil.getFish("ζ°΄ηΆεΊ§"));
        list.add(DataUtil.getFish("ιηεΊ§"));
        list.add(DataUtil.getFish("ε°ζεΊ§"));
        list.add(DataUtil.getFish("ε€ε₯³εΊ§"));
        if (Checker.hasList(items)) {
            items.clear();
            adapter.notifyDataSetChanged();
        }
        items.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void threadHandle(TypeData typeData) {
        String name = typeData.getName();
        if (name.equals(TypeDataNames.clickFisherViewFish)) {
            Fish bean = (Fish) typeData.getData();
            dialog = new ManageDialog(bean);
            dialog.show(getSupportFragmentManager(), ManageDialog.class.getSimpleName());
        }
        if (name.equals(TypeDataNames.updateAllFish)) {
            loadData();
        }
    }
}
