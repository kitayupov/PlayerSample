package com.kbnt.qam.mediacontroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MediaController extends View {

    private EventDetector eventDetector;

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

    private void initialize() {
        eventDetector = new EventDetector();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        eventDetector.onTouchEvent(this, event);
        return super.onTouchEvent(event);
    }

    public void setControlListener(ControlListener controlListener) {
        eventDetector.setControlListener(controlListener);
    }

    public interface ControlListener {
        void play();

        void pause();
    }
}
