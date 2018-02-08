package com.kbnt.qam.mediacontroller;

class SpeedController {

    private static final float DEFAULT_SPEED = 1.0F;
    private static final float MIN_SPEED = 0.0F;
    private static final float MAX_SPEED = 5.0F;
    private static final float STEP = 1.0F;

    private float speed = DEFAULT_SPEED;

    private MediaController.ControlCallback controlCallback;

    void setControlCallback(MediaController.ControlCallback controlCallback) {
        this.controlCallback = controlCallback;
    }

    float getSpeed() {
        return speed;
    }

    void reset() {
        setSpeed(DEFAULT_SPEED);
    }

    void decrease() {
        setSpeed(speed - STEP);
    }

    void increase() {
        setSpeed(speed + STEP);
    }

    private void setSpeed(float value) {
        speed = Math.max(MIN_SPEED, Math.min(value, MAX_SPEED));
        if (controlCallback != null && speed == value) {
            controlCallback.setSpeed(speed);
        }
    }
}
