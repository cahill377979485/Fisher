package com.cahill.fisher.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.cahill.fisher.base.BaseDialogFragment;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.databinding.DialogManageBinding;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.DataUtil;
import com.cahill.fisher.util.UIHelper;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 5:11
 * @desc
 */
public class ManageDialog extends BaseDialogFragment {

    private Fish fish;
    private DialogManageBinding binding;

    public ManageDialog(Fish fish) {
        this.fish = fish;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogManageBinding.inflate(LayoutInflater.from(getContext()));
        if (Checker.isNull(fish)) {
            Toast.makeText(getContext(), "数据异常", Toast.LENGTH_SHORT).show();
            dismiss();
            return;
        }
        UIHelper.setText(binding.etName, fish.getName());
        UIHelper.setText(binding.etHas, fish.getNum());
        UIHelper.setText(binding.etPriority, fish.getPriority());
        binding.tvCommit.setOnClickListener(v -> {
            try {
                fish.setNum(Integer.parseInt(UIHelper.getEtText(binding.etHas)));
                fish.setPriority(Integer.parseInt(UIHelper.getEtText(binding.etPriority)));
                DataUtil.saveFish(fish);
                dismiss();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public View getView() {
        return binding.getRoot();
    }
}
