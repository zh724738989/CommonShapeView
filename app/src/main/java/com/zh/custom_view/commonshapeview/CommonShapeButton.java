package com.zh.custom_view.commonshapeview;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import java.lang.reflect.Field;

import kotlin.jvm.internal.Intrinsics;

/**
 * 创建者：zhanghao
 * 创建时间：2018/8/20 11:34
 * 类说明：
 * 修订历史：
 */
public class CommonShapeButton extends AppCompatButton {
    private Context context;

    /**
     * shape模式
     * 矩形（rectangle）、椭圆形(oval)、线形(line)、环形(ring)
     */
    private int mShapeMode = 0;
    /**
     * 填充颜色
     */
    private int mFillColor = 0;
    /**
     * 按压颜色
     */
    private int mPressedColor = 0;
    /**
     * 描边颜色
     */
    private int mStrokeColor = 0;
    /**
     * 描边宽度
     */
    private int mStrokeWidth = 0;
    /**
     * 圆角半径
     */
    private float mCornerRadius = 0;
    /**
     * 圆角位置
     */
    private int mCornerPosition = 0;
    /**
     * 点击动效
     */
    private boolean mActiveEnable = false;
    /**
     * 起始颜色
     */
    private int mStartColor = 0;
    /**
     * 结束颜色
     */
    private int mEndColor = 0;
    /**
     * 渐变方向
     */
    private int mOrientation = 0;
    /**
     * drawable位置
     * -1-null、0-left、1-top、2-right、3-bottom
     */
    private int mDrawablePosition = -1;

    /**
     * 普通shape样式
     */
    private GradientDrawable normalGradientDrawable = new GradientDrawable();
    /**
     * 按压shape样式
     */
    private GradientDrawable pressedGradientDrawable = new GradientDrawable();
    /**
     * shape样式集合
     */
    private StateListDrawable stateListDrawable = new StateListDrawable();
    /**
     * button内容总宽度
     */
    private float contentWidth = 0f;
    /**
     * button内容总高度
     */
    private float contentHeight = 0f;

    private static final int TOP_LEFT = 1;
    private static final int TOP_RIGHT = 2;
    private static final int BOTTOM_RIGHT = 4;
    private static final int BOTTOM_LEFT = 8;

    public CommonShapeButton(Context context) {
        this(context, null);
    }

    public CommonShapeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonShapeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonShapeButton, defStyleAttr, 0);
        mShapeMode = a.getInt(R.styleable.CommonShapeButton_csb_shapeMode, 0);
        mFillColor = a.getColor(R.styleable.CommonShapeButton_csb_fillColor, Color.TRANSPARENT);
        mPressedColor = a.getColor(R.styleable.CommonShapeButton_csb_pressedColor, Color.TRANSPARENT);
        mStrokeColor = a.getColor(R.styleable.CommonShapeButton_csb_strokeColor, Color.TRANSPARENT);
        mStrokeWidth = a.getDimensionPixelSize(R.styleable.CommonShapeButton_csb_strokeWidth, dp2Px(0));
        mCornerRadius = a.getDimensionPixelSize(R.styleable.CommonShapeButton_csb_cornerRadius, dp2Px(0.0f));
        mCornerPosition = a.getInt(R.styleable.CommonShapeButton_csb_cornerPosition, -1);
        mActiveEnable = a.getBoolean(R.styleable.CommonShapeButton_csb_activeEnable, false);
        mDrawablePosition = a.getInt(R.styleable.CommonShapeButton_csb_drawablePosition, -1);
        mStartColor = a.getColor(R.styleable.CommonShapeButton_csb_startColor, Color.TRANSPARENT);
        mEndColor = a.getColor(R.styleable.CommonShapeButton_csb_endColor, Color.TRANSPARENT);
        mOrientation = a.getInt(R.styleable.CommonShapeButton_csb_orientation, 6);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (normalGradientDrawable != null) {
            //模式
            switch (this.mShapeMode) {
                case 0://矩形（rectangle）
                    normalGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                    break;
                case 1://椭圆形(oval)
                    normalGradientDrawable.setShape(GradientDrawable.OVAL);
                    break;
                case 2://线形(line)、
                    normalGradientDrawable.setShape(GradientDrawable.LINE);
                    break;
                case 3://环形(ring)
                    normalGradientDrawable.setShape(GradientDrawable.RING);
                    break;
            }

            //渐变色
            if (mStartColor != Color.TRANSPARENT && mEndColor != Color.TRANSPARENT) {
                normalGradientDrawable.setColors(new int[]{this.mStartColor, this.mEndColor});
                switch (mOrientation) {
                    case 0:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                        break;
                    case 1:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.TR_BL);
                        break;
                    case 2:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                        break;
                    case 3:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.BR_TL);
                        break;
                    case 4:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
                        break;
                    case 5:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.BL_TR);
                        break;
                    case 6:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                        break;
                    case 7:
                        normalGradientDrawable.setOrientation(GradientDrawable.Orientation.TL_BR);
                        break;
                }
            } else {//填充色
                normalGradientDrawable.setColor(mFillColor);
            }

            //统一设置圆角半径
            if (this.mCornerPosition == -1) {
                normalGradientDrawable.setCornerRadius(TypedValue.applyDimension(0, mCornerRadius, this.getResources().getDisplayMetrics()));
            } else {// 根据圆角位置设置圆角半径
                normalGradientDrawable.setCornerRadii(getCornerRadiusByPosition());
            }

            // 默认的透明边框不绘制,否则会导致没有阴影
            if (this.mStrokeColor != Color.TRANSPARENT) {
                normalGradientDrawable.setStroke(mStrokeWidth, mStrokeColor);
            }

            // 是否开启点击动效
            Drawable drawable;
            if (this.mActiveEnable) {
                // 5.0以上水波纹效果
                if (Build.VERSION.SDK_INT >= 21) {
                    drawable = new RippleDrawable(ColorStateList.valueOf(mPressedColor), normalGradientDrawable, null);
                } else {// 5.0以下变色效果
                    pressedGradientDrawable.setColor(mPressedColor);
                    switch (this.mShapeMode) {
                        case 0://矩形（rectangle）
                            pressedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                            break;
                        case 1://椭圆形(oval)
                            pressedGradientDrawable.setShape(GradientDrawable.OVAL);
                            break;
                        case 2://线形(line)、
                            pressedGradientDrawable.setShape(GradientDrawable.LINE);
                            break;
                        case 3://环形(ring)
                            pressedGradientDrawable.setShape(GradientDrawable.RING);
                            break;
                    }
                    //统一设置圆角半径
                    if (this.mCornerPosition == -1) {
                        pressedGradientDrawable.setCornerRadius(TypedValue.applyDimension(0, mCornerRadius, this.getResources().getDisplayMetrics()));
                    } else {// 根据圆角位置设置圆角半径
                        pressedGradientDrawable.setCornerRadii(getCornerRadiusByPosition());
                    }
                    // 默认的透明边框不绘制,否则会导致没有阴影
                    if (this.mStrokeColor != Color.TRANSPARENT) {
                        pressedGradientDrawable.setStroke(this.mStrokeWidth, this.mStrokeColor);
                    }
                    //按压效果
                    stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedGradientDrawable);
                    stateListDrawable.addState(new int[0], normalGradientDrawable);
                    drawable = stateListDrawable;
                }
            } else {
                drawable = normalGradientDrawable;
            }
            setBackground(drawable);
        }
    }

    // 如果xml中配置了drawable则设置padding让文字移动到边缘与drawable靠在一起
    // button中配置的drawable默认贴着边缘
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mDrawablePosition > -1) {
            if (getCompoundDrawables() != null) {
                Drawable drawable = this.getCompoundDrawables()[mDrawablePosition];
                if (drawable != null) {
                    int drawablePadding = getCompoundDrawablePadding();
                    int drawableHeight;
                    switch (mDrawablePosition) {
                        case 0:
                        case 2:
                            drawableHeight = drawable.getIntrinsicWidth();
                            float textWidth = this.getPaint().measureText(this.getText().toString());
                            this.contentWidth = textWidth + (float) drawableHeight + (float) drawablePadding;
                            int rightPadding = (int) ((float) this.getWidth() - this.contentWidth);
                            setPadding(0, 0, rightPadding, 0);
                            break;
                        case 1:
                        case 3:
                            drawableHeight = drawable.getIntrinsicHeight();
                            Paint.FontMetrics fm = this.getPaint().getFontMetrics();
                            float singeLineHeight = (float) Math.ceil((double) fm.descent - (double) fm.ascent);
                            float totalLineSpaceHeight = (float) (this.getLineCount() - 1) * this.getLineSpacingExtra();
                            float textHeight = singeLineHeight * (float) this.getLineCount() + totalLineSpaceHeight;
                            this.contentHeight = textHeight + (float) drawableHeight + (float) drawablePadding;
                            int bottomPadding = (int) ((float) this.getHeight() - this.contentHeight);
                            setPadding(0, 0, 0, bottomPadding);
                    }
                }
            }
        }

        setGravity(Gravity.CENTER);
        setClickable(true);
        changeTintContextWrapperToActivity();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        if (contentWidth > (float) 0 && (mDrawablePosition == 0 || mDrawablePosition == 2)) {
            canvas.translate(((float) this.getWidth() - contentWidth) / (float) 2, 0.0F);
        } else if (contentHeight > (float) 0 && (mDrawablePosition == 1 || mDrawablePosition == 3)) {
            canvas.translate(0.0F, ((float) this.getHeight() - contentHeight) / (float) 2);
        }
        super.onDraw(canvas);
    }

    private int dp2Px(float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    private int px2Dp(float px) {
        return (int) (px / context.getResources().getDisplayMetrics().density + 0.5f);
    }

    /**
     * 将本地资源图片大小缩放
     *
     * @param resId
     * @param w
     * @param h
     * @return
     */
    public Drawable zoomImage(int resId, int w, int h) {
        Resources res = context.getResources();
        Bitmap oldBmp = BitmapFactory.decodeResource(res, resId);
        Bitmap newBmp = Bitmap.createScaledBitmap(oldBmp, w, h, true);
        Drawable drawable = new BitmapDrawable(res, newBmp);
        return drawable;
    }

    /**
     * 根据圆角位置获取圆角半径
     */
    private final float[] getCornerRadiusByPosition() {
        float[] result = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
        if (containsFlag(mCornerPosition, TOP_LEFT)) {
            result[0] = mCornerRadius;
            result[1] = mCornerRadius;
        }

        if (containsFlag(mCornerPosition, TOP_RIGHT)) {
            result[2] = mCornerRadius;
            result[3] = mCornerRadius;
        }

        if (containsFlag(mCornerPosition, BOTTOM_RIGHT)) {
            result[4] = mCornerRadius;
            result[5] = mCornerRadius;
        }

        if (containsFlag(mCornerPosition, BOTTOM_LEFT)) {
            result[6] = mCornerRadius;
            result[7] = mCornerRadius;
        }

        return result;
    }

    /**
     * 是否包含对应flag
     */
    private final boolean containsFlag(int flagSet, int flag) {
        return (flagSet | flag) == flagSet;
    }

    /**
     * 从support23.3.0开始View中的getContext方法返回的是TintContextWrapper而不再是Activity
     * 如果使用xml注册onClick属性，就会通过反射到Activity中去找对应的方法
     * 5.0以下系统会反射到TintContextWrapper中去找对应的方法，程序直接crash
     * 所以这里需要针对5.0以下系统单独处理View中的getContext返回值
     */
    private final void changeTintContextWrapperToActivity() {
        if (Build.VERSION.SDK_INT < 21) {
            Activity activity = getActivity();
            if (activity != null) {
                Class clazz = this.getClass();
                while (clazz != null) {
                    try {
                        Field field = clazz.getDeclaredField("mContext");
                        field.setAccessible(true);
                        field.set(this, activity);
                        break;
                    } catch (Exception var6) {
                        var6.printStackTrace();
                        clazz = clazz.getSuperclass();
                    }
                }
            }
        }
    }

    /**
     * 从context中得到真正的activity
     */
    private final Activity getActivity() {
        for (Context context = this.getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

}
