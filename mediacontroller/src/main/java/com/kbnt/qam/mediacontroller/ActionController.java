package com.kbnt.qam.mediacontroller;

import android.app.Activity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ActionController implements View.OnTouchListener {

    private static final String TAG = ActionController.class.getSimpleName();

    private static final float SCROLL_SEPARATOR = 0.15F;
    private static final float DOUBLE_TAP_SEPARATOR = 0.40F;

    private static final int MAX_SCROLL_DEVIATION = 10;

    private final BrightnessController brightnessController;
    private final VolumeController volumeController;

    private MediaController.ControlCallback controlCallback;
    private GestureDetector gestureDetector;

    ActionController(Activity activity) {
        brightnessController = new BrightnessController(activity);
        volumeController = new VolumeController(activity);
    }

    private enum TouchPlace {LEFT, RIGHT, CENTER}

    private enum Status {PLAY, PAUSE}

    private Status status;

    private View view;

    void setControlCallback(MediaController.ControlCallback controlCallback) {
        this.status = Status.PLAY;
        this.controlCallback = controlCallback;
        gestureDetector = new GestureDetector(new ControlListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        this.view = view;
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private class ControlListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e(TAG, "onSingleTapUp: ");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e(TAG, "onLongPress: ");
            longClick();
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e(TAG, "onScroll: " + e1.getX() + " " + e2.getX()
                    + "\t" + e1.getY() + " " + e2.getY() + "\t" + distanceX + " " + distanceY);
            scroll(e1.getX(), e2.getX(), distanceY);
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
            doubleTap(e.getX());
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
            singleTap();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Log.e(TAG, "onContextClick: ");
            return super.onContextClick(e);
        }
    }

    private void singleTap() {
        switch (status) {
            case PLAY:
                pause();
                break;
            case PAUSE:
                play();
                break;
        }
    }

    private void play() {
        if (controlCallback != null) {
            controlCallback.play();
            status = Status.PLAY;
        }
    }

    private void pause() {
        if (controlCallback != null) {
            controlCallback.pause();
            status = Status.PAUSE;
        }
    }

    private void doubleTap(float pointX) {
        if (controlCallback != null) {
            final TouchPlace touchPlace = getTouchPlace(pointX, DOUBLE_TAP_SEPARATOR);
            switch (touchPlace) {
                case LEFT:
                    controlCallback.jumpLeft();
                    break;
                case RIGHT:
                    controlCallback.jumpRight();
                    break;
                default:
                    Log.e(TAG, "doubleTap: " + touchPlace.name());
            }
        }
    }

    private void longClick() {
        if (controlCallback != null) {
            controlCallback.longClick();
        }
    }

    private void scroll(float startX, float stopX, float distanceY) {
        if (controlCallback != null && (stopX - startX) < MAX_SCROLL_DEVIATION) {
            final TouchPlace scrollPlace = getScrollPlace(startX, stopX);
            final int relativeDistance = getRelativeDistance(distanceY);
            switch (scrollPlace) {
                case LEFT:
                    brightnessController.setBrightness(relativeDistance);
                    break;
                case RIGHT:
                    volumeController.setVolume(relativeDistance);
                    break;
                default:
                    Log.e(TAG, "scroll: " + scrollPlace.name());
            }
        }
    }

    private TouchPlace getScrollPlace(float startX, float stopX) {
        final TouchPlace startTouch = getTouchPlace(startX, SCROLL_SEPARATOR);
        final TouchPlace stopTouch = getTouchPlace(stopX, SCROLL_SEPARATOR);
        return startTouch.equals(stopTouch) ? startTouch : TouchPlace.CENTER;
    }

    private TouchPlace getTouchPlace(float pointX, float border) {
        final float width = view.getWidth();
        if (pointX >= 0 && pointX <= width * border) {
            return TouchPlace.LEFT;
        }
        if (pointX >= width * (1 - border) && pointX <= width) {
            return TouchPlace.RIGHT;
        }
        return TouchPlace.CENTER;
    }

    private int getRelativeDistance(float distanceY) {
        final float height = view.getHeight();
        return (int) (distanceY / height * 100);
    }
}
