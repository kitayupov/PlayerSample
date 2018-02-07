package com.kbnt.qam.mediacontroller;

import android.view.View;

public class MediaController {

    private final ActionController actionController;

    public MediaController(ControlCallback controlCallback) {
        actionController = new ActionController(controlCallback);
    }

    public void setWindow(View window) {
        window.setOnTouchListener(actionController);
    }

    public interface ControlCallback {
        void play();

        void pause();

        void longClick();

        void brightness(int value);

        void volume(int value);

        void jumpLeft();

        void jumpRight();
    }
}
