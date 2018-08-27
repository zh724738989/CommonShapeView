package com.zh.custom_view.commonshapeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建者：zhanghao
 * 创建时间：2018/8/14 15:32
 * 类说明：
 * 修订历史：
 */
public class CommonLinearLayoutHelper {
    private static final int DEFAULT_SEMICIRCLE_GAP = 4;
    private static final int DEFAULT_DASH_LINE_LENGTH = 10;
    private static final int DEFAULT_SEMICIRCLE_RADIUS = 4;
    private static final int DEFAULT_SEMICIRCLE_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_DASH_LINE_HEIGHT = 1;
    private static final int DEFAULT_DASH_LINE_GAP = 5;
    private static final int DEFAULT_DASH_LINE_COLOR = 0xFFFFFFFF;
    private static final int DEFAULT_DASH_LINE_MARGIN = 10;
    private static final int DEFAULT_DASH_SEMICIRCLE_MARGIN = 0;
    private static final int DEFAULT_DASH_SEMICIRCLE_SUM = 1;
    private static final float DEFAULT_STROKE_WIDTH = 0.0f;    // 默认边框宽度, 1dp
    private static final float DEFAULT_CORNER_RADIUS = 2.0f;   // 默认圆角半径, 2dp
    private static final float DEFAULT_LR_PADDING = 6f;      // 默认左右内边距
    private static final float DEFAULT_TB_PADDING = 2f;      // 默认上下内边距

    private Context context;

    private View view;

    //半圆画笔
    private Paint semicircleStokenPaint, semicirclePaint;

    //虚线画笔
    private Paint dashLinePaint;

    //外边框画笔
    private Paint frameOutPaint;

    //内边框画笔
    private Paint frameInPaint;

    //半圆之间间距
    private float semicircleGap = DEFAULT_SEMICIRCLE_GAP;

    //半圆半径
    private float semicircleRadius = DEFAULT_SEMICIRCLE_RADIUS;

    //半圆颜色
    private int semicircleColor = DEFAULT_SEMICIRCLE_COLOR;

    //半圆边框颜色
    private int semicircleStokenColor = DEFAULT_SEMICIRCLE_COLOR;

    //半圆数量X
    private int semicircleNumX = DEFAULT_DASH_SEMICIRCLE_SUM;

    //半圆数量Y
    private int semicircleNumY = DEFAULT_DASH_SEMICIRCLE_SUM;

    //绘制半圆曲线后X轴剩余距离
    private int remindSemicircleX;

    //绘制半圆曲线后Y轴剩余距离
    private int remindSemicircleY;

    //虚线的长度
    private float dashLineLength = DEFAULT_DASH_LINE_LENGTH;

    //虚线的高度
    private float dashLineHeight = DEFAULT_DASH_LINE_HEIGHT;

    //虚线的间距
    private float dashLineGap = DEFAULT_DASH_LINE_GAP;

    //虚线的颜色
    private int dashLineColor = DEFAULT_DASH_LINE_COLOR;

    //绘制虚线后X轴剩余距离
    private int remindDashLineX;

    //绘制虚线后Y轴剩余距离
    private int remindDashLineY;

    //虚线数量X
    private int dashLineNumX;

    //虚线数量Y
    private int dashLineNumY;

    //开启顶部半圆曲线
    private boolean isSemicircleTop = false;

    //开启底部半圆曲线
    private boolean isSemicircleBottom = false;

    //开启左边半圆曲线
    private boolean isSemicircleLeft = false;

    //开启右边半圆曲线
    private boolean isSemicircleRight = false;

    //开启顶部虚线
    private boolean isDashLineTop = false;

    //开启底部虚线
    private boolean isDashLineBottom = false;

    //开启左边虚线
    private boolean isDashLineLeft = true;

    //开启左边虚线
    private boolean isDashLineRight = true;

    //view宽度
    private int viewWidth;

    //view的高度
    private int viewHeight;

    //顶部虚线距离View顶部的距离
    private float dashLineMarginTop = DEFAULT_DASH_LINE_MARGIN;

    //底部虚线距离View底部的距离
    private float dashLineMarginBottom = DEFAULT_DASH_LINE_MARGIN;

    //左侧虚线距离View左侧的距离
    private float dashLineMarginLeft = DEFAULT_DASH_LINE_MARGIN;

    //右侧虚线距离View右侧的距离
    private float dashLineMarginRight = DEFAULT_DASH_LINE_MARGIN;

    //指定数量的半圆，X轴上第一个半圆距离view左边的距离
    private float semicircleMarginLeft = DEFAULT_DASH_SEMICIRCLE_MARGIN;

    //指定数量的半圆，Y轴上第一个半圆距离view顶部的距离
    private float semicircleMarginTop = DEFAULT_DASH_SEMICIRCLE_MARGIN;

    //控件背景颜色
    private int bgColor;

    // 边框线宽
    private int strokeWidth;

    // 边框颜色
    private int strokeColor;

    // 圆角半径
    private int cornerRadius;

    // 外边框矩形
    private RectF mOutRectF;

    private RectF mInRectF;

    public CommonLinearLayoutHelper(View view, Context context, AttributeSet attrs, int defStyle) {
        this.context = context;
        this.view = view;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonLinearLayout, defStyle, 0);
        semicircleRadius = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_semicircle_radius, dp2Px(DEFAULT_SEMICIRCLE_RADIUS));
        semicircleGap = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_semicircle_gap, dp2Px(DEFAULT_SEMICIRCLE_GAP));
        semicircleColor = a.getColor(R.styleable.CommonLinearLayout_cv_semicircle_color, DEFAULT_SEMICIRCLE_COLOR);
        semicircleStokenColor = a.getColor(R.styleable.CommonLinearLayout_cv_semicircle_stoken_color, DEFAULT_SEMICIRCLE_COLOR);

        dashLineGap = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_line_gap, dp2Px(DEFAULT_DASH_LINE_GAP));
        dashLineHeight = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_line_height, dp2Px(DEFAULT_DASH_LINE_HEIGHT));
        dashLineLength = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_line_length, dp2Px(DEFAULT_DASH_LINE_LENGTH));
        dashLineColor = a.getColor(R.styleable.CommonLinearLayout_cv_dash_line_color, DEFAULT_DASH_LINE_COLOR);

        isSemicircleTop = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_semicircle_top, isSemicircleTop);
        isSemicircleBottom = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_semicircle_bottom, isSemicircleBottom);
        isSemicircleLeft = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_semicircle_left, isSemicircleLeft);
        isSemicircleRight = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_semicircle_right, isSemicircleRight);
        isDashLineTop = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_dash_line_top, isDashLineTop);
        isDashLineBottom = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_dash_line_bottom, isDashLineBottom);
        isDashLineLeft = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_dash_line_left, isDashLineLeft);
        isDashLineRight = a.getBoolean(R.styleable.CommonLinearLayout_cv_is_dash_line_right, isDashLineRight);

        dashLineMarginTop = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_line_margin_top, dp2Px(DEFAULT_DASH_LINE_MARGIN));
        dashLineMarginBottom = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_line_margin_bottom, dp2Px(DEFAULT_DASH_LINE_MARGIN));
        dashLineMarginLeft = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_line_margin_left, dp2Px(DEFAULT_DASH_LINE_MARGIN));
        dashLineMarginRight = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_line_margin_right, dp2Px(DEFAULT_DASH_LINE_MARGIN));

        semicircleNumX = a.getInteger(R.styleable.CommonLinearLayout_cv_dash_semicircle_num_x, DEFAULT_DASH_SEMICIRCLE_SUM);
        semicircleNumY = a.getInteger(R.styleable.CommonLinearLayout_cv_dash_semicircle_num_y, DEFAULT_DASH_SEMICIRCLE_SUM);

        semicircleMarginTop = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_semicircle_margin_top, dp2Px(DEFAULT_DASH_SEMICIRCLE_MARGIN));
        semicircleMarginLeft = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_dash_semicircle_margin_left, dp2Px(DEFAULT_DASH_SEMICIRCLE_MARGIN));

        strokeWidth = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_frame_stroke_width, dp2Px(DEFAULT_STROKE_WIDTH));
        cornerRadius = a.getDimensionPixelSize(R.styleable.CommonLinearLayout_cv_frame_corner_radius, dp2Px(DEFAULT_CORNER_RADIUS));
        strokeColor = a.getColor(R.styleable.CommonLinearLayout_cv_frame_stroke_color, Color.TRANSPARENT);

        bgColor = a.getColor(R.styleable.CommonLinearLayout_cv_view_background_color, Color.TRANSPARENT);

        a.recycle();
        init();
    }

    /**
     * 初始化画笔
     */
    public void init() {
        //半圆画笔
        semicirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        semicirclePaint.setDither(true);
        semicirclePaint.setColor(semicircleStokenColor);
        semicirclePaint.setStrokeWidth(strokeWidth);
        semicirclePaint.setStyle(Paint.Style.STROKE);

        //圆形画笔
        semicircleStokenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        semicircleStokenPaint.setDither(true);
        semicircleStokenPaint.setColor(semicircleColor);
        semicircleStokenPaint.setStyle(Paint.Style.FILL);

        //虚线画笔
        dashLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dashLinePaint.setDither(true);
        dashLinePaint.setColor(dashLineColor);
        dashLinePaint.setStyle(Paint.Style.FILL);

        //外边框画笔
        mOutRectF = new RectF();
        frameOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        frameOutPaint.setDither(true);
        frameOutPaint.setAntiAlias(true);//设置画笔为无锯齿
        frameOutPaint.setColor(strokeColor);
        frameOutPaint.setStyle(Paint.Style.FILL);

        //内边框画笔
        mInRectF = new RectF();
        frameInPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        frameInPaint.setDither(true);
        frameInPaint.setAntiAlias(true);//设置画笔为无锯齿
        frameInPaint.setColor(bgColor);
        frameInPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * 设置view大小
     *
     * @param w
     * @param h
     */
    public void onSizeChanged(int w, int h) {
        viewWidth = w;
        viewHeight = h;
        calculate();
    }

    /**
     * 计算距离
     */
    public void calculate() {
        if (isSemicircleTop || isSemicircleBottom) {
            remindSemicircleX = (int) ((viewWidth - semicircleGap) % (2 * semicircleRadius + semicircleGap));
            if (semicircleNumX <= 0) {
                semicircleNumX = (int) ((viewWidth - semicircleGap) / (2 * semicircleRadius + semicircleGap));
            }
        }

        if (isSemicircleLeft || isSemicircleRight) {
            remindSemicircleY = (int) ((viewHeight - semicircleGap) % (2 * semicircleRadius + semicircleGap));
            if (semicircleNumY <= 0) {
                semicircleNumY = (int) ((viewHeight - semicircleGap) / (2 * semicircleRadius + semicircleGap));
            }
        }

        if (isDashLineTop || isDashLineBottom) {
            remindDashLineX = (int) ((viewWidth + dashLineGap - dashLineMarginLeft - dashLineMarginRight) % (dashLineLength + dashLineGap));
            dashLineNumX = (int) ((viewWidth + dashLineGap - dashLineMarginLeft - dashLineMarginRight) / (dashLineLength + dashLineGap));
        }

        if (isDashLineLeft || isDashLineRight) {
            remindDashLineY = (int) ((viewHeight + dashLineGap - dashLineMarginTop - dashLineMarginBottom) % (dashLineLength + dashLineGap));
            dashLineNumY = (int) ((viewHeight + dashLineGap - dashLineMarginTop - dashLineMarginBottom) / (dashLineLength + dashLineGap));
        }
    }

    /**
     * 开始绘制
     *
     * @param canvas
     */
    public void onDraw(Canvas canvas) {
        //外边框
        mOutRectF.left = mOutRectF.top = 0f;
        mOutRectF.right = viewWidth;
        mOutRectF.bottom = viewHeight;
        canvas.drawRoundRect(mOutRectF, cornerRadius, cornerRadius, frameOutPaint);

        //内边框
        mInRectF.left = mInRectF.top = strokeWidth;
        mInRectF.right = viewWidth - strokeWidth;
        mInRectF.bottom = viewHeight - strokeWidth;
        canvas.drawRoundRect(mInRectF, cornerRadius, cornerRadius, frameInPaint);

        if (isSemicircleTop) {
            for (int i = 0; i < semicircleNumX; i++) {
                float x = 0;
                if (semicircleMarginLeft <= 0) {
                    x = semicircleGap + semicircleRadius + remindSemicircleX / 2 + (semicircleGap + semicircleRadius * 2) * i;
                } else {
                    x = semicircleMarginLeft + (semicircleGap + semicircleRadius * 2) * i;
                }
                canvas.drawCircle(x, 0 + strokeWidth, semicircleRadius, semicirclePaint);
                canvas.drawCircle(x, 0 + strokeWidth, semicircleRadius, semicircleStokenPaint);
            }
        }

        if (isSemicircleBottom) {
            for (int i = 0; i < semicircleNumX; i++) {
                float x = 0;
                if (semicircleMarginLeft <= 0) {
                    x = semicircleGap + semicircleRadius + remindSemicircleX / 2 + (semicircleGap + semicircleRadius * 2) * i;
                } else {
                    x = semicircleMarginLeft + (semicircleGap + semicircleRadius * 2) * i;
                }
                canvas.drawCircle(x, viewHeight - strokeWidth, semicircleRadius, semicirclePaint);
                canvas.drawCircle(x, viewHeight - strokeWidth, semicircleRadius, semicircleStokenPaint);
            }
        }

        if (isSemicircleLeft) {
            for (int i = 0; i < semicircleNumY; i++) {
                float y = 0;
                if (semicircleMarginTop <= 0) {
                    y = semicircleGap + semicircleRadius + remindSemicircleY / 2 + (semicircleGap + semicircleRadius * 2) * i;
                } else {
                    y = semicircleMarginTop;
                }
                canvas.drawCircle(0 + strokeWidth, y, semicircleRadius, semicirclePaint);
                canvas.drawCircle(0 + strokeWidth, y, semicircleRadius, semicircleStokenPaint);
            }
        }

        if (isSemicircleRight) {
            for (int i = 0; i < semicircleNumY; i++) {
                float y = 0;
                if (semicircleMarginTop <= 0) {
                    y = semicircleGap + semicircleRadius + remindSemicircleY / 2 + (semicircleGap + semicircleRadius * 2) * i;
                } else {
                    y = semicircleMarginTop;
                }
                canvas.drawCircle(viewWidth - strokeWidth, y, semicircleRadius, semicirclePaint);
                canvas.drawCircle(viewWidth - strokeWidth, y, semicircleRadius, semicircleStokenPaint);
            }
        }

        if (isDashLineTop) {
            for (int i = 0; i < dashLineNumX; i++) {
                float x = dashLineMarginLeft + remindDashLineX / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(x, dashLineMarginTop, x + dashLineLength, dashLineMarginTop + dashLineHeight, dashLinePaint);
            }
        }

        if (isDashLineBottom) {
            for (int i = 0; i < dashLineNumX; i++) {
                float x = dashLineMarginLeft + remindDashLineX / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(x, viewHeight - dashLineHeight - dashLineMarginBottom, x + dashLineLength, viewHeight - dashLineMarginBottom, dashLinePaint);
            }
        }

        if (isDashLineLeft) {
            for (int i = 0; i < dashLineNumY; i++) {
                float y = dashLineMarginTop + remindDashLineY / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(dashLineMarginLeft, y, dashLineMarginLeft + dashLineHeight, y + dashLineLength, dashLinePaint);
            }
        }

        if (isDashLineRight) {
            for (int i = 0; i < dashLineNumY; i++) {
                float y = dashLineMarginTop + remindDashLineY / 2 + (dashLineGap + dashLineLength) * i;
                canvas.drawRect(viewWidth - dashLineMarginRight - dashLineHeight, y, viewWidth - dashLineMarginRight, y + dashLineLength, dashLinePaint);
            }
        }


    }

    private int dp2Px(float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    private int px2Dp(float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public float getSemicircleGap() {
        return px2Dp(semicircleGap);
    }

    public void setSemicircleGap(float semicircleGap) {
        if (this.semicircleGap != semicircleGap) {
            this.semicircleGap = semicircleGap;
            calculate();
            view.invalidate();
        }
    }

    public float getSemicircleRadius() {
        return px2Dp(semicircleRadius);
    }

    public void setSemicircleRadius(float semicircleRadius) {
        if (this.semicircleRadius != semicircleRadius) {
            this.semicircleRadius = semicircleRadius;
            calculate();
            view.invalidate();
        }
    }

    public int getSemicircleColor() {
        return semicircleColor;
    }

    public void setSemicircleColor(int semicircleColor) {
        if (this.semicircleColor != semicircleColor) {
            this.semicircleColor = semicircleColor;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineLength() {
        return px2Dp(dashLineLength);
    }

    public void setDashLineLength(float dashLineLength) {
        if (this.dashLineLength != dashLineLength) {
            this.dashLineLength = dashLineLength;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineHeight() {
        return px2Dp(dashLineHeight);
    }

    public void setDashLineHeight(float dashLineHeight) {
        if (this.dashLineHeight != dashLineHeight) {
            this.dashLineHeight = dashLineHeight;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineGap() {
        return px2Dp(dashLineGap);
    }

    public void setDashLineGap(float dashLineGap) {
        if (this.dashLineGap != dashLineGap) {
            this.dashLineGap = dashLineGap;
            calculate();
            view.invalidate();
        }
    }

    public int getDashLineColor() {
        return dashLineColor;
    }

    public void setDashLineColor(int dashLineColor) {
        if (this.dashLineColor != dashLineColor) {
            this.dashLineColor = dashLineColor;
            calculate();
            view.invalidate();
        }
    }

    public boolean isSemicircleTop() {
        return isSemicircleTop;
    }

    public void setSemicircleTop(boolean semicircleTop) {
        if (this.isSemicircleTop != semicircleTop) {
            isSemicircleTop = semicircleTop;
            calculate();
            view.invalidate();
        }
    }

    public boolean isSemicircleBottom() {
        return isSemicircleBottom;
    }

    public void setSemicircleBottom(boolean semicircleBottom) {
        if (isSemicircleBottom != semicircleBottom) {
            isSemicircleBottom = semicircleBottom;
            calculate();
            view.invalidate();
        }
    }

    public boolean isSemicircleLeft() {
        return isSemicircleLeft;
    }

    public void setSemicircleLeft(boolean semicircleLeft) {
        if (isSemicircleLeft != semicircleLeft) {
            isSemicircleLeft = semicircleLeft;
            calculate();
            view.invalidate();
        }
    }

    public boolean isSemicircleRight() {
        return isSemicircleRight;
    }

    public void setSemicircleRight(boolean semicircleRight) {
        if (isSemicircleRight != semicircleRight) {
            isSemicircleRight = semicircleRight;
            calculate();
            view.invalidate();
        }
    }

    public boolean isDashLineTop() {
        return isDashLineTop;
    }

    public void setDashLineTop(boolean dashLineTop) {
        if (isDashLineTop != dashLineTop) {
            isDashLineTop = dashLineTop;
            calculate();
            view.invalidate();
        }
    }

    public boolean isDashLineBottom() {
        return isDashLineBottom;
    }

    public void setDashLineBottom(boolean dashLineBottom) {
        if (isDashLineBottom != dashLineBottom) {
            isDashLineBottom = dashLineBottom;
            calculate();
            view.invalidate();
        }
    }

    public boolean isDashLineLeft() {
        return isDashLineLeft;
    }

    public void setDashLineLeft(boolean dashLineLeft) {
        if (isDashLineLeft != dashLineLeft) {
            isDashLineLeft = dashLineLeft;
            calculate();
            view.invalidate();
        }
    }

    public boolean isDashLineRight() {
        return isDashLineRight;
    }

    public void setDashLineRight(boolean dashLineRight) {
        if (isDashLineRight != dashLineRight) {
            isDashLineRight = dashLineRight;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineMarginTop() {
        return px2Dp(dashLineMarginTop);
    }

    public void setDashLineMarginTop(float dashLineMarginTop) {
        if (this.dashLineMarginTop != dashLineMarginTop) {
            this.dashLineMarginTop = dashLineMarginTop;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineMarginBottom() {
        return px2Dp(dashLineMarginBottom);
    }

    public void setDashLineMarginBottom(float dashLineMarginBottom) {
        if (this.dashLineMarginBottom != dashLineMarginBottom) {
            this.dashLineMarginBottom = dashLineMarginBottom;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineMarginLeft() {
        return px2Dp(dashLineMarginLeft);
    }

    public void setDashLineMarginLeft(float dashLineMarginLeft) {
        if (this.dashLineMarginLeft != dashLineMarginLeft) {
            this.dashLineMarginLeft = dashLineMarginLeft;
            calculate();
            view.invalidate();
        }
    }

    public float getDashLineMarginRight() {
        return px2Dp(dashLineMarginRight);
    }

    public void setDashLineMarginRight(float dashLineMarginRight) {
        if (this.dashLineMarginRight != dashLineMarginRight) {
            this.dashLineMarginRight = dashLineMarginRight;
            calculate();
            view.invalidate();
        }
    }

    public int getSemicircleNumX() {
        return semicircleNumX;
    }

    public void setSemicircleNumX(int semicircleNumX) {
        if (this.semicircleNumX != semicircleNumX) {
            this.semicircleNumX = semicircleNumX;
            calculate();
            view.invalidate();
        }
    }

    public int getSemicircleNumY() {
        return semicircleNumY;
    }

    public void setSemicircleNumY(int semicircleNumY) {
        if (this.semicircleNumY != semicircleNumY) {
            this.semicircleNumY = semicircleNumY;
            calculate();
            view.invalidate();
        }
    }

    public float getSemicircleMarginLeft() {
        return semicircleMarginLeft;
    }

    public void setSemicircleMarginLeft(float semicircleMarginLeft) {
        if (this.semicircleMarginLeft != semicircleMarginLeft) {
            this.semicircleMarginLeft = semicircleMarginLeft;
            calculate();
            view.invalidate();
        }
    }

    public float getSemicircleMarginTop() {
        return semicircleMarginTop;
    }

    public void setSemicircleMarginTop(float semicircleMarginTop) {
        if (this.semicircleMarginTop != semicircleMarginTop) {
            this.semicircleMarginTop = semicircleMarginTop;
            calculate();
            view.invalidate();
        }
    }
}
