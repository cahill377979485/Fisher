package com.cahill.fisher.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cahill.fisher.R;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/9/30 0030 下午 4:22
 * @desc
 */
public abstract class BaseSecondActivity extends BaseActivity {

    public void setTitle(String title) {
        ImageView iv = findViewById(R.id.iv_back);
        iv.setOnClickListener(v -> finish());
        TextView tv = findViewById(R.id.tv_title);
        tv.setText(title);
    }

    public void setTitle(int strId) {
        String title = getResources().getString(strId);
        setTitle(title);
    }

    public void setMore(View.OnClickListener listener) {
        ImageView iv = findViewById(R.id.iv_more);
        iv.setVisibility(View.VISIBLE);
        iv.setOnClickListener(listener);
    }

}
