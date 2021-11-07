package com.cahill.fisher.ui.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cahill.fisher.R;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.bean.TypeData;
import com.cahill.fisher.util.TypeDataNames;
import com.cahill.fisher.util.UIHelper;

import org.greenrobot.eventbus.EventBus;

import me.drakeet.multitype.ItemViewBinder;

public class FishBinder extends ItemViewBinder<Fish, FishBinder.VH> {

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VH(inflater.inflate(R.layout.item_fish, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull VH holder, @NonNull Fish item) {
        UIHelper.setText(holder.tvName, item.getName());
        UIHelper.setText(holder.tvNum, "拥有：" + item.getNum());
        int priority = item.getPriority();
        if (priority == 0) priority = item.getType();
        UIHelper.setText(holder.tvPriority, "优先级：" + priority);
        UIHelper.setBackgroundResource(holder.cl, priority > item.getType() ? R.drawable.btn_red_round10 : R.drawable.btn_blue_round10);
        UIHelper.setOnClickListener(holder.itemView, v -> EventBus.getDefault().post(new TypeData<>(TypeDataNames.clickFish, item)));
    }

    static class VH extends RecyclerView.ViewHolder {
        ConstraintLayout cl;
        TextView tvName, tvNum, tvPriority;

        public VH(@NonNull View itemView) {
            super(itemView);
            cl = itemView.findViewById(R.id.cl);
            tvName = itemView.findViewById(R.id.tv);
            tvNum = itemView.findViewById(R.id.tv_num);
            tvPriority = itemView.findViewById(R.id.tv_priority);
        }
    }
}
