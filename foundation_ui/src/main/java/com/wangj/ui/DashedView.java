package com.wangj.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wangj.core.util.ViewUtil;


/**
 * 虚线View，可以实现水平虚线、竖直虚线、斜虚线
 * Created by ThinkPad S5-WangJ on 2018/3/8.
 * TODO 代码还需要优化，onMeasure
 * TODO 未添加必要属性的set方法，Java动态添加View时不完善
 */
public class DashedView extends View {

    private float size;
    private float dashed;
    private float dashGap;
    private int dashedColor;
    private int direction;

    private Paint paint;

    private float[] pts;

    public DashedView(Context context) {
        super(context);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); // 本View关闭硬件加速，否则画出来是实线

        size = ViewUtil.dp2px(context, 1f);
        dashed = ViewUtil.dp2px(context, 5);
        dashGap = ViewUtil.dp2px(context, 2);
        dashedColor = Color.BLACK;
        direction = 0;

        initPaint();
    }

    public DashedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); // 本View关闭硬件加速，否则画出来是实线

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DashedView);
        size = typedArray.getDimension(R.styleable.DashedView_size, ViewUtil.dp2px(context, 0.5f));
        dashed = typedArray.getDimension(R.styleable.DashedView_dashed, ViewUtil.dp2px(context, 5));
        dashGap = typedArray.getDimension(R.styleable.DashedView_dashedGap, ViewUtil.dp2px(context, 2));
        dashedColor = typedArray.getColor(R.styleable.DashedView_dashedColor, Color.BLACK);
        direction = typedArray.getInt(R.styleable.DashedView_direction, 0);
        typedArray.recycle();

        initPaint();
    }

    void initPaint() {
        paint = new Paint();
//        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(dashedColor);
        paint.setStrokeWidth(size); // 线宽

        // 设置虚线效果
        PathEffect pathEffect = new DashPathEffect(new float[]{dashed, dashGap}, 0);
        paint.setPathEffect(pathEffect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float startX = 0;
        float startY = 0;
        float endX = 0;
        float endY = 0;

        switch (direction) {
            case 0:
                // 水平虚线
                startX = getPaddingLeft();
                startY = (getHeight() - getPaddingBottom() + getPaddingTop()) / 2;
                endX = getWidth() - getPaddingRight();
                endY = startY;
                break;

            case 1:
                // 竖直虚线
                startX = (getWidth() - getPaddingRight() + getPaddingLeft()) / 2;
                startY = getPaddingTop();
                endX = startX;
                endY = getHeight() - getPaddingBottom();
                break;

            case 2:
                // 左上角到右下角的虚线
                startX = getPaddingLeft();
                startY = getPaddingTop();
                endX = getWidth() - getPaddingRight();
                endY = getHeight() - getPaddingBottom();
                break;

            case 3:
                // 右上角到左下角的虚线
                startX = getWidth() - getPaddingRight();
                startY = getPaddingTop();
                endX = getPaddingLeft();
                endY = getHeight() - getPaddingBottom();
                break;
        }

        pts = new float[]{startX, startY, endX, endY};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLines(pts, paint);
    }
}
