package com.kbnt.qam.mediacontroller;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

class EventDetector {

    private static final String TAG = MediaController.class.getSimpleName();

    private MediaController.ControlListener controlListener;
    private GestureDetector gestureDetector;

    private enum Status {PLAY, PAUSE}

    private Status status;

    EventDetector() {
        this.status = Status.PLAY;
        gestureDetector = new GestureDetector(new Listener());
    }

    void setControlListener(MediaController.ControlListener controlListener) {
        this.controlListener = controlListener;
    }

    void onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
    }

    private class Listener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e(TAG, "onSingleTapUp: ");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e(TAG, "onLongPress: ");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(TAG, "onScroll: ");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e(TAG, "onFling: ");
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.e(TAG, "onShowPress: ");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.e(TAG, "onDown: ");
            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.e(TAG, "onDoubleTap: ");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.e(TAG, "onDoubleTapEvent: ");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.e(TAG, "onSingleTapConfirmed: ");
            switch (status) {
                case PLAY:
                    pause();
                    break;
                case PAUSE:
                    play();
                    break;
            }
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Log.e(TAG, "onContextClick: ");
            return super.onContextClick(e);
        }
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
}
