package com.kbnt.qam.mediacontroller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MediaController extends FrameLayout {

    private SpeedControls speedControls;

    public MediaController(@NonNull Context context) {
        this(context, null);
    }

    public MediaController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public void setControlCallback(ControlCallback controlCallback) {
        setOnTouchListener(new ActionController(controlCallback));
        speedControls.setControlCallback(controlCallback);
    }

    private void initialize(Context context) {
        final LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View root = inflate.inflate(R.layout.layout_controller, null);
        final LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        root.setLayoutParams(layoutParams);
        speedControls = new SpeedControls(root);
        addView(root);
    }

    public interface ControlCallback {
        void play();

        void pause();

        void longClick();

        void brightness(int value);

        void volume(int value);

        void jumpLeft();

        void jumpRight();

        void setSpeed(float value);
    }
}
