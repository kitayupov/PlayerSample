package com.kbnt.qam.mediacontroller;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

class EventDetector {

    private static final String TAG = MediaController.class.getSimpleName();

    private MediaController.ControlListener controlListener;

    private enum Status {PLAY, PAUSE}

    private Status status;

    EventDetector() {
        this.status = Status.PLAY;
    }

    void setControlListener(MediaController.ControlListener controlListener) {
        this.controlListener = controlListener;
    }

    void onTouchEvent(View view, MotionEvent event) {
        onTouchListener.onTouch(view, event);
    }

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
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
}
