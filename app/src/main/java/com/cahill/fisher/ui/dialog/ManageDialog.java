package com.cahill.fisher.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.cahill.fisher.base.BaseDialogFragment;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.databinding.DialogManageBinding;
import com.cahill.fisher.util.Checker;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 5:11
 * @desc
 */
public class ManageDialog extends BaseDialogFragment {

    private Fish fish;
    private DialogManageBinding binding;

    public ManageDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogManageBinding.inflate(LayoutInflater.from(getContext()));

        if (Checker.notNull(fish)) {

        }
    }

    public ManageDialog setFish(Fish fish) {
        this.fish = fish;
        return this;
    }
}
