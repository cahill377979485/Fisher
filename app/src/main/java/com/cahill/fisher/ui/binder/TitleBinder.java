package com.cahill.fisher.ui.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cahill.fisher.R;
import com.cahill.fisher.bean.TitleBean;
import com.cahill.fisher.util.UIHelper;

import me.drakeet.multitype.ItemViewBinder;

public class TitleBinder extends ItemViewBinder<TitleBean, TitleBinder.VH> {

    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new VH(inflater.inflate(R.layout.item_title, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull VH holder, @NonNull TitleBean item) {
        UIHelper.setText(holder.tv, item.getTitle());
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
