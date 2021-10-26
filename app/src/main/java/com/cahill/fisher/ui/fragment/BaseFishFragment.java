package com.cahill.fisher.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.cahill.fisher.R;
import com.cahill.fisher.base.BaseFragment;
import com.cahill.fisher.databinding.FragmentMarketBinding;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/10/26 0026 上午 9:33
 * @desc
 */
public class BaseFishFragment extends BaseFragment {
    private FragmentMarketBinding binding;
    private Items items = new Items();
    private MultiTypeAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        adapter = new MultiTypeAdapter(items);

//        binding.rv.setLayoutManager(new Gri);
    }

    @Override
    protected void initData() {

    }
}
