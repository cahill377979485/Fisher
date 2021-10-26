package com.cahill.fisher.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cahill.fisher.MyApp;
import com.cahill.fisher.R;
import com.cahill.fisher.base.BaseDialogFragment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/8/18 0018 上午 10:19
 * @desc
 */
public class UIHelper {
    /**
     * 设置变色文本，灰色+橙色
     *
     * @param activity 上下文
     * @param tv       文本控件
     * @param strings  文本数组
     */
    public static void setSpanTextGreenWithActivity(Activity activity, TextView tv, String... strings) {
        setSpanTextGreen(activity, tv, strings);
    }

    /**
     * 设置黑灰色和绿色混排的文字
     *
     * @param context 上下文
     * @param tv      文本控件
     * @param strings 字符串们
     */
    public static void setSpanTextGreen(Context context, TextView tv, String... strings) {
        if (Checker.isNull(context) || Checker.isNull(tv)) return;
        if (Checker.isNull(strings) || strings.length == 0) return;
        SpanUtils spanUtils = new SpanUtils();
        for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 0) {
                if (Checker.hasWord(strings[i]))
                    spanUtils.append(strings[i]).setForegroundColor(ContextCompat.getColor(context, R.color.color_black));
            } else {
                if (Checker.hasWord(strings[i]))
                    spanUtils.append(strings[i]).setForegroundColor(ContextCompat.getColor(context, R.color.color_green_main));
            }
        }
        tv.setText(spanUtils.create());
    }

    /**
     * 设置黑灰色和绿色混排的文字
     *
     * @param context 上下文
     * @param tv      文本控件
     * @param strings 字符串们
     */
    public static void setSpanTextGray(Context context, TextView tv, String... strings) {
        if (Checker.isNull(context) || Checker.isNull(tv)) return;
        if (Checker.isNull(strings) || strings.length == 0) return;
        SpanUtils spanUtils = new SpanUtils();
        for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 0) {
                if (Checker.hasWord(strings[i]))
                    spanUtils.append(strings[i]).setForegroundColor(ContextCompat.getColor(context, R.color.color_black_33));
            } else {
                if (Checker.hasWord(strings[i]))
                    spanUtils.append(strings[i]).setForegroundColor(ContextCompat.getColor(context, R.color.color_gray_999999));
            }
        }
        tv.setText(spanUtils.create());
    }

    /**
     * 设置蓝绿渐变色文字
     *
     * @param textView 文字控件
     */
    public static void setSpanGradientBlueGreen(TextView textView) {
        float endX = textView.getPaint().getTextSize() * textView.getText().length();
        LinearGradient linearGradient = new LinearGradient(0f, 0f, endX, 0f,
                Color.parseColor("#ff1dc8e4"),
                Color.parseColor("#ff1de6ba"),
                Shader.TileMode.CLAMP
        );
        textView.getPaint().setShader(linearGradient);
        textView.invalidate();
    }

    /**
     * 取消蓝绿渐变色文字
     *
     * @param textView 文字控件
     */
    public static void cancelSpanGradientBlueGreen(TextView textView) {
        textView.getPaint().setShader(null);
        textView.invalidate();
    }

    /**
     * 设置文字大小相错文本
     *
     * @param activity 上下文
     * @param tv       文本控件
     * @param strings  文本数组
     */
    public static void setSpanTextBigSmall(Activity activity, TextView tv, String... strings) {
        setSpanTextBigSmall(activity, tv, strings);
    }

    /**
     * 设置文字大小相错文本
     *
     * @param context 上下文
     * @param tv      文本控件
     * @param strings 文本数组
     */
    public static void setSpanTextBigSmall(Context context, TextView tv, String... strings) {
        if (Checker.isNull(context) || Checker.isNull(tv)) return;
        if (Checker.isNull(strings) || strings.length == 0) return;
        SpanUtils spanUtils = new SpanUtils();
        for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 0) {
                if (Checker.hasWord(strings[i]))
                    spanUtils.append(strings[i]).setFontSize((int) getPx(R.dimen.b50)).setBold();
            } else {
                if (Checker.hasWord(strings[i]))
                    spanUtils.append(strings[i]).setFontSize((int) getPx(R.dimen.b22));
            }
        }
        tv.setText(spanUtils.create());
    }

    /**
     * 为文本控件设置大数字小单位
     *
     * @param tv  文本控件
     * @param str 文本
     */
    public static void setTextWith50BigNumber25SmallUnit(TextView tv, String str) {
        if (Checker.isNull(tv)) return;
        if (Checker.noWord(str)) return;
        String numStr = "0123456789.-+/*%";
        SpanUtils spanUtils = new SpanUtils();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if (numStr.contains(s)) {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b50)).setBold();
            } else {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b25));
            }
        }
        tv.setText(spanUtils.create());
    }

    /**
     * 为文本控件设置大数字小单位
     *
     * @param tv  文本控件
     * @param str 文本
     */
    public static void setTextWith60BigNumber40SmallUnit(TextView tv, String str) {
        if (Checker.isNull(tv)) return;
        if (Checker.noWord(str)) return;
        String numStr = "0123456789.-+/*%";
        SpanUtils spanUtils = new SpanUtils();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if (numStr.contains(s)) {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b60)).setBold();
            } else {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b40));
            }
        }
        tv.setText(spanUtils.create());
    }

    /**
     * 为文本控件设置大数字小单位
     *
     * @param tv  文本控件
     * @param str 文本
     */
    public static void setTextWith50BigNumber22SmallUnit(TextView tv, String str) {
        if (Checker.isNull(tv)) return;
        if (Checker.noWord(str)) return;
        String numStr = "0123456789.-+/*%";
        SpanUtils spanUtils = new SpanUtils();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if (numStr.contains(s)) {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b50)).setBold();
            } else {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b22));
            }
        }
        tv.setText(spanUtils.create());
    }

    /**
     * 为文本控件设置大数字小单位
     *
     * @param tv  文本控件
     * @param str 文本
     */
    public static void setTextWith79BigNumber34SmallUnit(TextView tv, String str) {
        if (Checker.isNull(tv)) return;
        if (Checker.noWord(str)) return;
        String numStr = "0123456789.-+/*%";
        SpanUtils spanUtils = new SpanUtils();
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if (numStr.contains(s)) {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b79)).setBold();
            } else {
                spanUtils.append(s).setFontSize((int) getPx(R.dimen.b34));
            }
        }
        tv.setText(spanUtils.create());
    }

    /**
     * 为文本控件设置文字，text可以为空字符串（""），统一用这个方法，避免空指针异常
     *
     * @param tv   文本控件
     * @param text 显示的字符串，字符串为null时，控件不显示内容
     */
    public static void setText(TextView tv, String text) {
        if (Checker.isNull(text)) text = "";
        if (text.equals("null")) text = "";
        if (Checker.notNull(tv))
            tv.setText(text);
    }

    /**
     * 设置文本
     *
     * @param tv  文本控件
     * @param num 数值
     */
    public static void setText(TextView tv, int num) {
        setText(tv, String.valueOf(num));
    }

    /**
     * 设置文本
     *
     * @param tv  文本控件
     * @param num 数值
     */
    public static void setText(TextView tv, long num) {
        setText(tv, String.valueOf(num));
    }

    /**
     * 设置文本
     *
     * @param tv  文本控件
     * @param num 数值
     */
    public static void setText(TextView tv, double num) {
        setText(tv, String.valueOf(num));
    }

    /**
     * 设置文本
     *
     * @param tv  文本控件
     * @param num 数值
     */
    public static void setText(TextView tv, float num) {
        setText(tv, String.valueOf(num));
    }

    /**
     * 设置文本
     *
     * @param tv  文本控件
     * @param num 数值
     */
    public static void setText(TextView tv, short num) {
        setText(tv, String.valueOf(num));
    }

    /**
     * 设置文本
     *
     * @param tv  文本控件
     * @param num 数值
     */
    public static void setText(TextView tv, byte num) {
        setText(tv, String.valueOf(num));
    }

    /**
     * 设置文本。当文本为空的时候不显示null
     *
     * @param tv  文本控件
     * @param str 文本内容
     */
    public void setTextNotNull(TextView tv, String str) {
        if (Checker.isNull(str)) str = "";
        setText(tv, str);
    }

    /**
     * 设置文本字体大小
     *
     * @param tv   文本控件
     * @param size 字体大小
     */
    public static void setTextSize(TextView tv, float size) {
        if (Checker.isNull(tv)) return;
        tv.setTextSize(size);
    }

    /**
     * 设置输入框的光标位置
     *
     * @param et    输入框
     * @param index 光标目标位置
     */
    public static void setSelection(EditText et, int index) {
        if (Checker.isNull(et)) return;
        et.setSelection(index);
    }

    /**
     * 设置文字颜色
     *
     * @param tv      控件
     * @param colorId 颜色编号
     */
    public static void setTextColor(TextView tv, int colorId) {
        if (Checker.isNull(tv) || colorId <= 0) return;
        if (Checker.isNull(MyApp.getMyApplicationContext())) return;
        tv.setTextColor(ContextCompat.getColor(MyApp.getMyApplicationContext(), colorId));
    }

    /**
     * 设置可用不可用
     *
     * @param view   控件
     * @param enable 可用否
     */
    public static void setEnabled(View view, boolean enable) {
        if (Checker.isNull(view)) return;
        view.setEnabled(enable);
    }

    /**
     * 设置背景样式资源
     *
     * @param view  控件
     * @param resId 资源编号
     */
    public static void setBackgroundResource(View view, int resId) {
        if (Checker.isNull(view)) return;
        view.setBackgroundResource(resId);
    }

    /**
     * 设置背景样式颜色
     *
     * @param view  控件
     * @param resId 资源编号
     */
    public static void setBackgroundColor(View view, @ColorRes int resId) {
        if (Checker.isNull(view)) return;
        view.setBackgroundColor(ContextCompat.getColor(MyApp.getMyApplicationContext(), resId));
    }

    /**
     * 设置控件是否可被点击
     *
     * @param view      控件
     * @param clickable 是否可被点击
     */
    public static void setClickable(View view, boolean clickable) {
        if (Checker.isNull(view)) return;
        view.setClickable(clickable);
    }

    /**
     * 设置显示或隐藏
     *
     * @param view       控件
     * @param visibility 显隐的类型编码，0-显示,4-隐藏,8-消失
     */
    public static void setVisibility(View view, int visibility) {
        if (Checker.isNull(view)) return;
        if (view.getVisibility() != visibility)
            view.setVisibility(visibility);
    }

    /**
     * 设置显示或隐藏
     *
     * @param view    控件
     * @param visible 是否显示
     */
    public static void setVisibleInvisible(View view, boolean visible) {
        if (Checker.isNull(view)) return;
        view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置显示或消失
     *
     * @param view    控件
     * @param visible 是否显示
     */
    public static void setVisibleGone(View view, boolean visible) {
        if (Checker.isNull(view)) return;
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    /**
     * 批量设置显示或者隐藏
     *
     * @param visible 是否显示
     * @param views   控件数组
     */
    public static void setVisibleGone(boolean visible, View... views) {
        if (views.length <= 0) return;
        for (View view : views) {
            if (Checker.notNull(view)) view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 批量设置显示或者隐藏
     *
     * @param visible 是否显示
     * @param views   控件数组
     */
    public static void setVisibleInvisible(boolean visible, View... views) {
        if (views.length <= 0) return;
        for (View view : views) {
            view.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    /**
     * 返回控件是否可见
     *
     * @param view 控件
     * @return 所求
     */
    public static boolean isVisible(View view) {
        if (Checker.isNull(view)) return false;
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * 返回控件是否不可见
     *
     * @param view 控件
     * @return 所求
     */
    public static boolean isInvisible(View view) {
        if (Checker.isNull(view)) return false;
        return view.getVisibility() == View.INVISIBLE;
    }

    /**
     * 返回控件是否消失不见
     *
     * @param view 控件
     * @return 所求
     */
    public static boolean isGone(View view) {
        if (Checker.isNull(view)) return false;
        return view.getVisibility() == View.GONE;
    }

    /**
     * 设置点击事件
     *
     * @param view     控件
     * @param listener 点击事件监听
     */
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        if (Checker.isNull(view)) return;
        if (Checker.isNull(listener)) return;
        view.setOnClickListener(listener);
    }

    /**
     * 设置控件显示本地图片
     *
     * @param view  图片控件
     * @param resId 本地图片的资源编号
     */
    public static void setImageResource(ImageView view, int resId) {
        if (Checker.isNull(view)) return;
        view.setImageResource(resId);
    }

    /**
     * 设置控件显示本地图片
     *
     * @param view   图片控件
     * @param bitmap 本地图片的资源
     */
    public static void setImageBitmap(ImageView view, Bitmap bitmap) {
        if (Checker.isNull(view)) return;
        view.setImageBitmap(bitmap);
    }


    /**
     * 释放倒计时
     *
     * @param timer 倒计时
     */
    public static void releaseTimer(CountDownTimer timer) {
        if (Checker.notNull(timer)) {
            timer.cancel();
            timer = null;
        }
    }

    /**
     * 释放值动画
     *
     * @param valueAnimator 值动画执行者
     */
    public static void releaseValueAnimator(ValueAnimator valueAnimator) {
        if (valueAnimator != null) {
            valueAnimator.cancel();
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.removeAllListeners();
            valueAnimator = null;
        }
    }

    /**
     * 执行动画
     *
     * @param view 控件
     * @param anim 动画
     */
    public static void startAnimation(View view, Animation anim) {
        if (Checker.isNull(view)) return;
        view.startAnimation(anim);
    }

    /**
     * 设置tag标记
     *
     * @param view 控件
     * @param obj  标记
     */
    public static void setTag(View view, Object obj) {
        if (Checker.isNull(view)) return;
        view.setTag(obj);
    }

    /**
     * 获取tag标记
     *
     * @param view 控件
     * @return 所求
     */
    public static Object getTag(View view) {
        Object tag = null;
        if (Checker.notNull(view))
            tag = view.getTag();
        return tag;
    }

    /**
     * 获取输入框字符串
     *
     * @param et 输入框控件
     * @return 所求
     */
    public static String getEtText(EditText et) {
        if (Checker.isNull(et)) return "";
        return et.getText().toString().trim();
    }

    /**
     * 开始帧动画
     *
     * @param drawable 帧动画
     */
    public static void startAnimationDrawable(AnimationDrawable drawable) {
        if (Checker.isNull(drawable)) return;
        drawable.start();
    }

    /**
     * 结束帧动画
     *
     * @param drawable 帧动画
     */
    public static void stopAnimationDrawable(AnimationDrawable drawable) {
        if (Checker.isNull(drawable)) return;
        drawable.stop();
    }

    /**
     * 获取控件的字符串
     *
     * @param tv 控件
     * @return 所求
     */
    public static String getText(TextView tv) {
        if (Checker.isNull(tv)) return "";
        return tv.getText().toString();
    }

    /**
     * 滑动列表到指定位置
     *
     * @param rv    列表
     * @param index 位置
     */
    public static void scrollToPosition(RecyclerView rv, int index) {
        if (Checker.isNull(rv)) return;
        if (index < 0) index = 0;
        rv.scrollToPosition(index);
    }

    /**
     * 平滑地滑动到指定位置
     *
     * @param rv    列表
     * @param index 位置
     */
    public static void smoothScrollTo(RecyclerView rv, int index) {
        if (Checker.isNull(rv)) return;
        if (index < 0) index = 0;
        rv.smoothScrollToPosition(index);
    }

    /**
     * 释放处理器
     *
     * @param handler 处理器
     */
    public static void releaseHandler(Handler handler) {
        if (Checker.isNull(handler)) return;
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }

    /**
     * 模拟点击
     *
     * @param view 控件
     */
    public static void performClick(View view) {
        if (Checker.isNull(view)) return;
        view.performClick();
    }

    /**
     * 设置文本控件右边的图标
     *
     * @param tv    文本控件
     * @param redId 图标编号
     */
    public static void setDrawableEnd(TextView tv, int redId) {
        if (Checker.isNull(tv)) return;
        Drawable drawable = MyApp.getMyApplicationContext().getResources().getDrawable(redId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 返回当前设备适配的长度
     *
     * @param dimenId dimen的编号
     * @return 所求
     */
    public static float getPx(int dimenId) {
        Context context = MyApp.getMyApplicationContext();
        if (Checker.notNull(context) && Checker.notNull(context.getResources()))
            return context.getResources().getDimensionPixelSize(dimenId);
        else return 10;
    }

    /**
     * 返回颜色资源对应的颜色值
     *
     * @param colorId 颜色资源编号
     * @return 所求
     */
    public static int getColor(int colorId) {
        return ContextCompat.getColor(MyApp.getMyApplicationContext(), colorId);
    }

    /**
     * 添加输入监听
     *
     * @param et      输入框
     * @param watcher 文字监听
     */
    public static void addTextChangedListener(EditText et, TextWatcher watcher) {
        if (Checker.isNull(et)) return;
        et.addTextChangedListener(watcher);
    }

    /**
     * 返回指定的字符串
     *
     * @param strResId 字符串资源编号
     * @return 所求
     */
    public static String getStr(int strResId) {
        return MyApp.getMyApplicationContext().getString(strResId);
    }

    /**
     * 当以逗号结尾时，返回去除尾巴逗号的字符串
     *
     * @param str 字符串
     * @return 所求
     */
    public static String removeLastCommaIfExist(String str) {
        str = str.replace("，", ",");
        if (str.contains(",")) str = str.substring(0, str.length() - 1);
        return str;
    }

    /**
     * 关闭弹窗
     *
     * @param dialog
     */
    public static void dismiss(BaseDialogFragment dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = null;
    }

    /**
     * 关闭弹窗
     *
     * @param dialog
     */
    public static void dismiss(Dialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = null;
    }
}
