package com.cahill.fisher.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import com.cahill.fisher.R;
import com.cahill.fisher.bean.Fish;
import com.cahill.fisher.bean.TypeData;
import com.cahill.fisher.util.Checker;
import com.cahill.fisher.util.TypeDataNames;
import com.cahill.fisher.util.UIHelper;
import com.cahill.fisher.util.Val;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author 文琳_377979485@qq.com
 * @time 2021/10/20 0020 下午 1:52
 * @desc 显示星座鱼的兑换关系。类似脑图。
 */
public class FisherRelationshipView extends View {
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

    private Bitmap[] bitmaps = new Bitmap[Val.TYPE_BGS.length];
    private Bitmap bitmapFish;
    private Bitmap bitmapPriority;

    private static final float ITEM_WIDTH = 350;
    private static final float ITEM_HEIGHT = 350;

    private Fish fish;
    private float lastX, lastY;
    private static final float MAX_CLICK_DISTANCE = 5;//点击时，手指按下离抬起的位置最多这么多。超出这个值视为拖动而非点击。
    private boolean editMode;
    private boolean showHelper;

    public FisherRelationshipView(Context context) {
        this(context, null);
    }

    public FisherRelationshipView(Context context, @Nullable AttributeSet attrs) {
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
        textPaint.setColor(colorWhite);

        textPaintSmall = new TextPaint();
        textPaintSmall.setAntiAlias(true);
        textPaintSmall.setDither(true);
        textPaintSmall.setTextSize(40);
        textPaintSmall.setFakeBoldText(true);
        textPaintSmall.setColor(colorWhite);

        rect = new Rect();
        path = new Path();

        for (int i = 0; i < Val.TYPE_BGS.length; i++) {
            bitmaps[i] = handleBitmap(BitmapFactory.decodeResource(getResources(), Val.TYPE_BGS[i]), ITEM_WIDTH, ITEM_HEIGHT);
        }

        bitmapFish = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_fish);
        bitmapFish = handleBitmap(bitmapFish, 42, 31);
        bitmapPriority = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_priority);
        bitmapPriority = handleBitmap(bitmapPriority, 42, 31);
    }

    public void setFish(Fish fish) {
        this.fish = fish;
        invalidate();
    }

    private Bitmap getBitmap(Fish fish) {
        if (Checker.isNull(fish)) {
            return bitmaps[0];
        }
        return bitmaps[fish.getType()];
    }

    /**
     * 生成新的Bitmap图片
     *
     * @return 所求
     */
    private Bitmap handleBitmap(Bitmap bitmap, float newWidth, float newHeight) {
        Matrix matrix = new Matrix();//创建一个处理图片的类
        int width = bitmap.getWidth();//获取图片本身的大小(宽)
        int height = bitmap.getHeight();//获取图片本身的大小(高)
        float wS = newWidth / (width > 0 ? width : newWidth);//缩放比---->这块注意这个是新的宽度/高度除以旧的宽度
        float hS = newHeight / (height > 0 ? height : newHeight);//缩放比---->这块注意这个是新的宽度/高度除以旧的宽度
        matrix.postScale(wS, hS);//这块就是处理缩放的比例
        //matrix.setScale(sX,sY);//缩放图片的质量sX表示宽0.5就代表缩放一半,sX同样
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (Checker.isNull(fish)) return;
        //name
        String name = fish.getName();
        textPaint.getTextBounds(name, 0, name.length(), rect);
        float startX = getWidth() / 6f + ITEM_WIDTH / 2f;
        float startY = getHeight() / 2f;
        canvas.drawBitmap(getBitmap(fish), getWidth() / 6f - ITEM_WIDTH / 2f, getHeight() / 2f - ITEM_HEIGHT / 2f, paint);
        canvas.drawText(name, getWidth() / 6f - rect.width() / 2f, getHeight() / 2f - (rect.bottom + rect.top) / 2f, textPaint);
        //num bitmap first, text second
        canvas.drawBitmap(bitmapFish, getWidth() / 6f - getWidth() / 10f, getHeight() / 2f + ITEM_HEIGHT / 3f - bitmapFish.getHeight() / 2f, paint);
        String num = String.valueOf(fish.getNum());
        textPaintSmall.getTextBounds(num, 0, num.length(), rect);
        canvas.drawText(num, getWidth() / 6f - getWidth() / 10f + bitmapFish.getWidth() * 3 / 2f, getHeight() / 2f + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
        //priority text first, bitmap second
        String priority = String.valueOf(fish.getPriority());
        textPaintSmall.getTextBounds(priority, 0, priority.length(), rect);
        canvas.drawText(priority, getWidth() / 6f + getWidth() / 10f - rect.width(), getHeight() / 2f + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
        canvas.drawBitmap(bitmapPriority, getWidth() / 6f + getWidth() / 10f - rect.width() - bitmapPriority.getWidth() * 3 / 2f, getHeight() / 2f + ITEM_HEIGHT / 3f - bitmapPriority.getHeight() / 2f, paint);
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
                canvas.drawBitmap(getBitmap(fishParent), endXParent, itemHeight * (i + 0.5f) - ITEM_HEIGHT / 2f, paint);
                canvas.drawText(nameParent, getWidth() / 2f - rect.width() / 2f, itemHeight * (i + 0.5f) - (rect.bottom + rect.top) / 2f, textPaint);
                //num
                canvas.drawBitmap(bitmapFish, getWidth() / 2f - getWidth() / 10f, itemHeight * (i + 0.5f) + ITEM_HEIGHT / 3f - bitmapFish.getHeight() / 2f, paint);
                String numParent = String.valueOf(fishParent.getNum());
                textPaintSmall.getTextBounds(numParent, 0, numParent.length(), rect);
                canvas.drawText(numParent, getWidth() / 2f - getWidth() / 10f + bitmapFish.getWidth() * 3 / 2f, itemHeight * (i + 0.5f) + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
                //priority
                String priorityParent = String.valueOf(fishParent.getPriority());
                textPaintSmall.getTextBounds(priorityParent, 0, priorityParent.length(), rect);
                canvas.drawText(priorityParent, getWidth() / 2f + getWidth() / 10f - rect.width(), itemHeight * (i + 0.5f) + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
                canvas.drawBitmap(bitmapPriority, getWidth() / 2f + getWidth() / 10f - rect.width() - bitmapPriority.getWidth() * 3 / 2f, itemHeight * (i + 0.5f) + ITEM_HEIGHT / 3f - bitmapFish.getHeight() / 2f, paint);
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
                        canvas.drawBitmap(getBitmap(fishGrandParent), endXGrandParent, endYGrandParent - ITEM_HEIGHT / 2f, paint);
                        canvas.drawText(nameGrandParent, getWidth() * 5 / 6f - rect.width() / 2f, endYGrandParent - (rect.bottom + rect.top) / 2f, textPaint);
                        //num
                        canvas.drawBitmap(bitmapFish, getWidth() * 5 / 6f - getWidth() / 10f, endYGrandParent + ITEM_HEIGHT / 3f - bitmapFish.getHeight() / 2f, paint);
                        String numGrandParent = String.valueOf(fishGrandParent.getNum());
                        textPaintSmall.getTextBounds(numGrandParent, 0, numGrandParent.length(), rect);
                        canvas.drawText(numGrandParent, getWidth() * 5 / 6f - getWidth() / 10f + bitmapFish.getWidth() * 3 / 2f, endYGrandParent + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
                        //priority
                        String priorityGrandParent = String.valueOf(fishGrandParent.getPriority());
                        textPaintSmall.getTextBounds(priorityGrandParent, 0, priorityGrandParent.length(), rect);
                        canvas.drawText(priorityGrandParent, getWidth()*5 / 6f + getWidth() / 10f - rect.width(), endYGrandParent + ITEM_HEIGHT / 3f - (rect.bottom + rect.top) / 2f, textPaintSmall);
                        canvas.drawBitmap(bitmapPriority, getWidth()*5 / 6f + getWidth() / 10f - rect.width() - bitmapPriority.getWidth() * 3 / 2f, endYGrandParent + ITEM_HEIGHT / 3f - bitmapFish.getHeight() / 2f, paint);
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                return true;
            case MotionEvent.ACTION_UP:
                if (Math.abs(x - lastX) > MAX_CLICK_DISTANCE || Math.abs(y - lastY) > MAX_CLICK_DISTANCE)
                    return false;
                if (Checker.notNull(fish)) {
                    if (x > getWidth() / 6f - ITEM_WIDTH / 2f && x < getWidth() / 6f + ITEM_WIDTH / 2f && y > getHeight() / 2f - ITEM_HEIGHT / 2f && y < getHeight() / 2f + ITEM_HEIGHT / 2f) {
                        EventBus.getDefault().post(new TypeData<>(TypeDataNames.clickFisherViewFish, fish));
                        return true;
                    }
                    List<Fish> listParent = fish.getProducer();
                    if (Checker.hasList(listParent)) {
                        float itemHeightParent = getHeight() / (float) listParent.size();
                        for (int i = 0; i < listParent.size(); i++) {
                            float startYParent = itemHeightParent * i;
                            Fish fishParent = listParent.get(i);
                            if (x > getWidth() / 2f - ITEM_WIDTH / 2f && x < getWidth() / 2f + ITEM_WIDTH / 2f && y > startYParent + itemHeightParent / 2f - ITEM_HEIGHT / 2f && y < startYParent + itemHeightParent / 2f + ITEM_HEIGHT / 2f) {
                                EventBus.getDefault().post(new TypeData<>(TypeDataNames.clickFisherViewFish, fishParent));
                                return true;
                            }
                            List<Fish> listGrandParent = fishParent.getProducer();
                            if (Checker.hasList(listGrandParent)) {
                                float itemHeightGrandParent = itemHeightParent / listGrandParent.size();
                                for (int j = 0; j < listGrandParent.size(); j++) {
                                    float startYGrandParent = startYParent + itemHeightGrandParent * j;
                                    Fish fishGrandParent = listGrandParent.get(j);
                                    if (x > getWidth() * 5 / 6f - ITEM_WIDTH / 2f && x < getWidth() * 5 / 6f + ITEM_WIDTH / 2f && y > startYGrandParent + itemHeightGrandParent / 2f - ITEM_HEIGHT / 2f && y < startYGrandParent + itemHeightGrandParent / 2f + ITEM_HEIGHT / 2f) {
                                        EventBus.getDefault().post(new TypeData<>(TypeDataNames.clickFisherViewFish, fishGrandParent));
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            default:
        }
        return super.onTouchEvent(event);
    }
}
