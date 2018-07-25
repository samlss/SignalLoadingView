package com.iigo.library;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import static android.view.View.MeasureSpec.AT_MOST;

/**
 * @author SamLeung
 * @e-mail 729717222@qq.com
 * @github https://github.com/samlss
 * @csdn https://blog.csdn.net/Samlss
 * @description The signal loading view.
 */
public class SignalLoadingView extends View {
    private final static int DEFAULT_DURATION = 1000;
    private final static int DEFAULT_COLOR = Color.WHITE;

    private int signalColor = DEFAULT_COLOR;

    private float circleRadius;
    private float centerCircleRadius;
    private float strokeWidth;

    private float centerX;
    private float centerY;

    private Paint signalPaint;
    private Paint centerPaint;

    private ObjectAnimator objectAnimator;
    private TimeInterpolator interpolator = new AccelerateDecelerateInterpolator();
    private long duration = DEFAULT_DURATION;

    public SignalLoadingView(Context context) {
        super(context);

        init();
    }

    public SignalLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        parseAttr(attrs);
        init();
    }

    public SignalLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttr(attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SignalLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        parseAttr(attrs);
        init();
    }

    private void parseAttr(AttributeSet attrs){
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SignalLoadingView);
        signalColor = typedArray.getColor(R.styleable.SignalLoadingView_signal_color, DEFAULT_COLOR);
        duration = typedArray.getInteger(R.styleable.SignalLoadingView_duration, DEFAULT_DURATION);
        int interpolatorValue = typedArray.getInt(R.styleable.SignalLoadingView_interpolator, 0);

        switch (interpolatorValue){
            case 0:
                interpolator = new AccelerateDecelerateInterpolator();
                break;

            case 1:
                interpolator = new AccelerateInterpolator();
                break;

            case 2:
                interpolator = new DecelerateInterpolator();
                break;

            case 3:
                interpolator = new BounceInterpolator();
                break;

            case 4:
                interpolator = new CycleInterpolator(0.5f);
                break;

            case 5:
                interpolator = new LinearInterpolator();
                break;

            case 6:
                interpolator = new AnticipateOvershootInterpolator();
                break;

            case 7:
                interpolator = new AnticipateInterpolator();
                break;

            case 8:
                interpolator = new OvershootInterpolator();
                break;

                default:
                    break;
        }

        typedArray.recycle();
    }

    private void init(){
        signalPaint = new Paint();
        signalPaint.setAntiAlias(true);
        signalPaint.setColor(signalColor);
        signalPaint.setStyle(Paint.Style.STROKE);

        centerPaint = new Paint();
        centerPaint.setAntiAlias(true);
        centerPaint.setColor(signalColor);
        centerPaint.setStyle(Paint.Style.FILL);
    }

    private void setupAnimator(){
        objectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0, 360);
        objectAnimator.setInterpolator(interpolator);
        objectAnimator.setDuration(duration);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int modeWidth  = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //没有指定宽高,使用了wrap_content,则手动指定宽高为MATCH_PARENT
        // (No width or height is specified, wrap_content is used, and the width and height are manually specified as MATCH_PARENT)
        if (modeWidth == AT_MOST && modeHeight == AT_MOST){
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            setLayoutParams(layoutParams);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,
                h, oldw, oldh);

        stop();
        int minSize = Math.min(w, h);
        strokeWidth = minSize / 50f;
        circleRadius = (minSize) / 2 - 2 * strokeWidth;
        centerCircleRadius = circleRadius / 10f;

        signalPaint.setStrokeWidth(strokeWidth);

        centerX = w / 2;
        centerY = h / 2;

        setupAnimator();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(centerX, centerY);
        canvas.drawCircle(0, 0, circleRadius, signalPaint);
        canvas.drawCircle(0, 0, centerCircleRadius, centerPaint);

        RectF oval1 = new RectF(  - circleRadius * 6 / 8f,  - circleRadius * 6 / 8f,  circleRadius * 6 / 8f,  circleRadius * 6 / 8f);
        RectF oval2 = new RectF(  - circleRadius * 5 / 8f,  - circleRadius * 5 / 8f,  circleRadius * 5 / 8f,  circleRadius * 5 / 8f);
        RectF oval3 = new RectF(  - circleRadius * 4 / 8f,  - circleRadius * 4 / 8f,  circleRadius * 4 / 8f,  circleRadius * 4 / 8f);

        canvas.drawArc(oval1, -45,90, false, signalPaint);
        canvas.drawArc(oval2, -45,90, false, signalPaint);
        canvas.drawArc(oval3, -45,90, false, signalPaint);

        canvas.drawArc(oval1, 135,90, false, signalPaint);
        canvas.drawArc(oval2, 135,90, false, signalPaint);
        canvas.drawArc(oval3, 135,90, false, signalPaint);
    }

    public void setSignalColor(int signalColor) {
        this.signalColor = signalColor;
        signalPaint.setColor(signalColor);
        centerPaint.setColor(signalColor);
        invalidate();
    }

    public void setDuration(long duration) {
        this.duration = duration;
        stop();
        setupAnimator();
    }

    public void setInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
        stop();
        setupAnimator();
    }

    public void start(){
        if (objectAnimator != null){
            objectAnimator.start();
        }
    }

    public void stop(){
        if (objectAnimator != null){
            objectAnimator.cancel();
        }
    }
}
