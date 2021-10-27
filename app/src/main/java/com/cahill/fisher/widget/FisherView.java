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
import android.view.MotionEvent;
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
    private TextPaint textPaint, textPaintSmall;
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

    private static final float ITEM_WIDTH = 360;
    private static final float ITEM_HEIGHT = 360;
    private static final float ROUND_RADIUS = 20;

    private LinearGradient linearGradient, linearGradientChild;

    private Fish fish;
    private boolean editMode;
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
        textPaint.setFakeBoldText(true);
        textPaint.setColor(colorBlue);

        textPaintSmall = new TextPaint();
        textPaintSmall.setAntiAlias(true);
        textPaintSmall.setDither(true);
        textPaintSmall.setTextSize(40);
        textPaintSmall.setFakeBoldText(true);
        textPaintSmall.setColor(colorWhite);

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
        //画名称
        String name = fish.getName();
        textPaint.getTextBounds(name, 0, name.length(), rect);
        //渐变色
        paint.setShader(getLinearGradient());
        float startX = getWidth() / 6f + ITEM_WIDTH / 2f;
        float startY = getHeight() / 2f;
        canvas.drawRoundRect(getWidth() / 6f - ITEM_WIDTH / 2f, getHeight() / 2f - ITEM_HEIGHT / 2f, startX, getHeight() / 2f + ITEM_HEIGHT / 2f, ROUND_RADIUS, ROUND_RADIUS, paint);
        canvas.drawText(name, getWidth() / 6f - rect.width() / 2f, getHeight() / 2f - (rect.bottom + rect.top) / 2f, textPaint);
        //num
        String num = String.valueOf(fish.getHas());
        textPaintSmall.getTextBounds(num, 0, num.length(), rect);
        textPaintSmall.setColor(colorWhite);
        canvas.drawText(num, getWidth() / 6f - rect.width() / 2f, getHeight() / 2f + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
        //
        List<Fish> listParent = fish.getProducer();
        if (Checker.hasList(listParent)) {
            float itemHeight = getHeight() / (float) listParent.size();
            for (int i = 0; i < listParent.size(); i++) {
                if (showHelper) {
                    canvas.drawLine(0, itemHeight * (i + 1), getWidth() * 2 / 3f, itemHeight * (i + 1), textPaint);
                    canvas.drawLine(0, itemHeight * (i + 1) - itemHeight / 2f, getWidth() * 2 / 3f, itemHeight * (i + 1) - itemHeight / 2f, textPaint);
                }
                Fish fishParent = listParent.get(i);
                String nameParent = fishParent.getName();
                textPaint.getTextBounds(nameParent, 0, nameParent.length(), rect);
                float endXParent = getWidth() / 2f - ITEM_WIDTH / 2f;
                float endYParent = itemHeight * (i + 0.5f);
                float startX2 = getWidth() / 2f + ITEM_WIDTH / 2f;
                path.reset();
                path.moveTo(startX, startY);
                float centerX = (endXParent + startX) / 2f;
                path.cubicTo(centerX, startY, centerX, endYParent, endXParent, endYParent);
                canvas.drawPath(path, paintLine);
                paint.setShader(getLinearGradient());
                canvas.drawRoundRect(endXParent, itemHeight * (i + 0.5f) - ITEM_HEIGHT / 2f, getWidth() / 2f + ITEM_WIDTH / 2f, itemHeight * (i + 0.5f) + ITEM_HEIGHT / 2f, ROUND_RADIUS, ROUND_RADIUS, paint);
                canvas.drawText(nameParent, getWidth() / 2f - rect.width() / 2f, itemHeight * (i + 0.5f) - (rect.bottom + rect.top) / 2f, textPaint);
                //num
                String numParent = fishParent.getHas() + "/" + fishParent.getNeed();
                textPaintSmall.getTextBounds(numParent, 0, numParent.length(), rect);
                textPaintSmall.setColor(fishParent.getHas() > fishParent.getNeed() ? colorWhite : colorRed);
                canvas.drawText(numParent, getWidth() / 2f - rect.width() / 2f, itemHeight * (i + 0.5f) + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
                List<Fish> listGrandParent = fishParent.getProducer();
                if (Checker.hasList(listGrandParent)) {
                    float itemHeight2 = itemHeight / (float) listGrandParent.size();
                    for (int j = 0; j < listGrandParent.size(); j++) {
                        if (showHelper) {
                            canvas.drawLine(getWidth() * 2 / 3f, itemHeight2 * (j + 1), getWidth(), itemHeight2 * (j + 1), textPaint);
                            canvas.drawLine(getWidth() * 2 / 3f, itemHeight2 * (j + 1) - itemHeight2 / 2f, getWidth(), itemHeight2 * (j + 1) - itemHeight2 / 2f, textPaint);
                        }
                        Fish fishGrandParent = listGrandParent.get(j);
                        String nameGrandParent = fishGrandParent.getName();
                        textPaint.getTextBounds(nameGrandParent, 0, nameGrandParent.length(), rect);
                        float endXGrandParent = getWidth() * 5 / 6f - ITEM_WIDTH / 2f;
                        float endYGrandParent = itemHeight * i + itemHeight2 * (j + 0.5f);
                        path.reset();
                        path.moveTo(startX2, endYParent);
                        float centerXParent = (endXGrandParent + startX2) / 2f;
                        path.cubicTo(centerXParent, endYParent, centerXParent, endYGrandParent, endXGrandParent, endYGrandParent);
                        canvas.drawPath(path, paintLine);
                        paint.setShader(getLinearGradient());
                        canvas.drawRoundRect(endXGrandParent, endYGrandParent - ITEM_HEIGHT / 2f, getWidth() * 5 / 6f + ITEM_WIDTH / 2f, itemHeight * i + itemHeight2 * (j + 0.5f) + ITEM_HEIGHT / 2f, ROUND_RADIUS, ROUND_RADIUS, paint);
                        canvas.drawText(nameGrandParent, getWidth() * 5 / 6f - rect.width() / 2f, endYGrandParent - (rect.bottom + rect.top) / 2f, textPaint);
                        //num
                        String numGrandParent = fishGrandParent.getHas() + "/" + fishGrandParent.getNeed();
                        textPaintSmall.getTextBounds(numGrandParent, 0, numGrandParent.length(), rect);
                        textPaintSmall.setColor(fishGrandParent.getHas() > fishGrandParent.getNeed() ? colorWhite : colorRed);
                        canvas.drawText(numGrandParent, getWidth() * 5 / 6f - rect.width() / 2f, endYGrandParent + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        return super.onTouchEvent(event);
    }
}
