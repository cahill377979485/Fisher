package com.cahill.fisher.ui.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cahill.fisher.R;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.widget.FisherRelationshipView;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/10/26 0026 下午 1:23
 * @desc
 */
public class FishRelationshipBinder extends ItemViewBinder<Fish, FishRelationshipBinder.VH> {

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VH(inflater.inflate(R.layout.item_fish_relationship, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull VH holder, @NonNull Fish item) {
        if (Checker.notNull(holder.fv)) holder.fv.setFish(item);
    }

    static class VH extends RecyclerView.ViewHolder {
        FisherRelationshipView fv;

        public VH(@NonNull View itemView) {
            super(itemView);
            fv = itemView.findViewById(R.id.fv);
        }
    }
}
