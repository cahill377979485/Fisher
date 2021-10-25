package com.cahill.fisher.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.cahill.fisher.R;
import com.cahill.fisher.base.BaseSecondActivity;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.databinding.ActivityRelationshipBinding;
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
        adapter = new MultiTypeAdapter();
//        adapter.register();
        binding = ActivityRelationshipBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setTitle(R.string.relationship);
        setMore(v -> {
        });

        if (Checker.noList(DataUtil.getAllFish())) {
            List<Fish> list = new ArrayList<>();
            Fish cake = new Fish("蛋糕鱼", null, "蛋糕石", 800, 1038, false);
            DataUtil.saveFish(cake);
            Fish bear = new Fish("比熊", null, "比熊石", 800, 705, false);
            DataUtil.saveFish(bear);
            Fish puff = new Fish("泡芙", null, "泡芙石", 800, 1929, false);
            DataUtil.saveFish(puff);
            list.add(cake);
            list.add(bear);
            list.add(puff);
            Fish aquarius = new Fish("水瓶座", list, "水瓶石", 0, 252, false);
            DataUtil.saveFish(aquarius);
        }

        Fish aquarius = DataUtil.getFish("水瓶座");
        binding.fv.setFish(aquarius);

    }
}
