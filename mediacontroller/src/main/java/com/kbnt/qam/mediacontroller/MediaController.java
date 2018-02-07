package com.kbnt.qam.mediacontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MediaController extends View {

    private ControlDetector controlDetector;

    public MediaController(Context context) {
        this(context, null);
    }

    public MediaController(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaController(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MediaController(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final Paint redLinePaint = new Paint();
        redLinePaint.setStrokeWidth(4.0F);
        redLinePaint.setColor(Color.RED);

        final float leftRed = 0.15F * getWidth();
        final float rightRed = (1 - 0.15F) * getWidth();

        drawLine(canvas, leftRed, redLinePaint);
        drawLine(canvas, rightRed, redLinePaint);

        final Paint blueLinePaint = new Paint();
        blueLinePaint.setStrokeWidth(4.0F);
        blueLinePaint.setColor(Color.BLUE);

        final float leftBlue = 0.40F * getWidth();
        final float rightBlue = (1 - 0.40F) * getWidth();

        drawLine(canvas, leftBlue, blueLinePaint);
        drawLine(canvas, rightBlue, blueLinePaint);
    }

    private void drawLine(Canvas canvas, float x, Paint paint) {
        canvas.drawLine(x, 0, x, getHeight(), paint);
    }

    private void initialize() {
        controlDetector = new ControlDetector(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controlDetector.onTouchEvent(event);
        return true;
    }

    public void setControlListener(ControlListener controlListener) {
        controlDetector.setControlListener(controlListener);
    }

    public interface ControlListener {
        void play();

        void pause();

        void longClick();

        void brightness(int value);

        void volume(int value);

        void jumpLeft();

        void jumpRight();
    }
}
