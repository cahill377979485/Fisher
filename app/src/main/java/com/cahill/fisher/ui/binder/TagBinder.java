package com.cahill.fisher.ui.binder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cahill.fisher.R;
import com.cahill.fisher.bean.TagBean;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 3:55
 * @desc
 */
public class TagBinder extends ItemViewBinder<TagBean, TagBinder.TagHolder> {

    @NonNull
    @Override
    protected TagHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new TagHolder(inflater.inflate(R.layout.item_tag, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull TagBinder.TagHolder holder, @NonNull TagBean item) {
        holder.tv.setText(item.getName());
        holder.itemView.setOnClickListener(item.getListener());
    }

    static class TagHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TagHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
