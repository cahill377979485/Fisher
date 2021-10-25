package com.cahill.fisher.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.util.Checker;

import java.util.List;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/10/20 0020 下午 1:52
 * @desc 显示星座鱼的兑换关系。类似脑图。
 */
public class FisherView extends View {
    private Paint paint, paintLine;
    private TextPaint textPaint;
    private Rect rect;
    private Path path;
    @ColorInt
    private int colorBlack = Color.BLACK;
    @ColorInt
    private int colorBlue = Color.BLUE;
    @ColorInt
    private int colorWhite = Color.WHITE;
    @ColorInt
    private int colorGray = Color.GRAY;
    @ColorInt
    private int colorRed = Color.RED;

    private static final float ITEM_WIDTH = 600;
    private static final float ITEM_HEIGHT = 100;
    private static final float ROUND_RADIUS = ITEM_HEIGHT / 2f;

    private LinearGradient linearGradient, linearGradientChild;

    private Fish fish;
    private boolean showHelper;

    public FisherView(Context context) {
        this(context, null);
    }

    public FisherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);

        paintLine = new Paint();
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(4);
        paintLine.setStrokeCap(Paint.Cap.ROUND);
        paintLine.setAntiAlias(true);
        paintLine.setDither(true);
        paintLine.setColor(colorRed);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setTextSize(60);
        textPaint.setColor(colorWhite);

        rect = new Rect();
        path = new Path();
    }

    public void setFish(Fish fish) {
        this.fish = fish;
        invalidate();
    }

    private LinearGradient getLinearGradient() {
        if (Checker.isNull(linearGradient))
            linearGradient = new LinearGradient(0f, 0f, 0f, getHeight(),
                    Color.parseColor("#ff1dc9e3"),//蓝色
                    Color.parseColor("#ff1de6ba"),//绿色
                    Shader.TileMode.CLAMP
            );
        return linearGradient;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Checker.isNull(fish)) return;
        //
        String name = fish.getName();
        textPaint.measureText(name);
        textPaint.getTextBounds(name, 0, name.length(), rect);
        //渐变色
        paint.setShader(getLinearGradient());
        float startX = getWidth() / 4f + rect.width() / 2f + ROUND_RADIUS;
        float startY = getHeight() / 2f;
        canvas.drawRoundRect(getWidth() / 4f - rect.width() / 2f - ROUND_RADIUS, getHeight() / 2f - ITEM_HEIGHT / 2f, startX, getHeight() / 2f + ITEM_HEIGHT / 2f, ROUND_RADIUS, ROUND_RADIUS, paint);
        canvas.drawText(name, getWidth() / 4f - rect.width() / 2f, getHeight() / 2f - (rect.bottom + rect.top) / 2f, textPaint);
        //
        List<Fish> list = fish.getProducer();
        if (Checker.hasList(list)) {
            float itemHeight = getHeight() / (float) list.size();
            for (int i = 0; i < list.size(); i++) {
                if(showHelper) {
                    canvas.drawLine(0, itemHeight * (i + 1), getWidth(), itemHeight * (i + 1), textPaint);
                    canvas.drawLine(0, itemHeight * (i + 1) - itemHeight / 2f, getWidth(), itemHeight * (i + 1) - itemHeight / 2f, textPaint);
                }
                Fish f = list.get(i);
                String n = f.getName();
                textPaint.getTextBounds(n, 0, n.length(), rect);
                float endX = getWidth() * 3 / 4f - rect.width() / 2f - ROUND_RADIUS;
                float endY = itemHeight * (i + 0.5f);
                path.reset();
                path.moveTo(startX, startY);
                path.quadTo(startX, endY, endX, endY);
                canvas.drawPath(path, paintLine);
                paint.setShader(getLinearGradient());
                canvas.drawRoundRect(endX, itemHeight * (i + 0.5f) - ITEM_HEIGHT / 2f, getWidth() * 3 / 4f + rect.width() / 2f + ROUND_RADIUS, itemHeight * (i + 0.5f) + ITEM_HEIGHT / 2f, ROUND_RADIUS, ROUND_RADIUS, paint);
                canvas.drawText(n, getWidth() * 3 / 4f - rect.width() / 2f, itemHeight * (i + 0.5f) - (rect.bottom + rect.top) / 2f, textPaint);
            }
        }
    }
}
