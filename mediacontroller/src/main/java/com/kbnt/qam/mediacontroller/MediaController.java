package com.kbnt.qam.mediacontroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MediaController extends View {

    private static final String TAG = MediaController.class.getSimpleName();

    private ControlListener controlListener;

    private enum Status {PLAY, PAUSE}

    private Status status;

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
        status = Status.PLAY;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        onTouchListener.onTouch(this, event);
        return super.onTouchEvent(event);
    }

    public void setControlListener(ControlListener controlListener) {
        this.controlListener = controlListener;
    }

    private OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.e(TAG, "onTouch: " + event.getAction());
            switch (status) {
                case PLAY:
                    pause();
                    break;
                case PAUSE:
                    play();
                    break;
            }
            return false;
        }

        private void play() {
            if (controlListener != null) {
                controlListener.play();
                status = Status.PLAY;
            }
        }

        private void pause() {
            if (controlListener != null) {
                controlListener.pause();
                status = Status.PAUSE;
            }
        }
    };

    public interface ControlListener {
        void play();

        void pause();
    }
}
