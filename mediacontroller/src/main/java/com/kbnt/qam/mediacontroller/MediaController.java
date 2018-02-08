package com.kbnt.qam.mediacontroller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Locale;

public class MediaController extends FrameLayout {

    private SpeedController speedController;
    private TextView currentSpeedTextView;

    public MediaController(@NonNull Context context) {
        this(context, null);
    }

    public MediaController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        speedController = new SpeedController();
        initialize(context);
    }

    public void setControlCallback(ControlCallback controlCallback) {
        setOnTouchListener(new ActionController(controlCallback));
        speedController.setControlCallback(controlCallback);
    }

    private void initialize(Context context) {
        final LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View controllers = inflate.inflate(R.layout.layout_controller, null);
        final LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        controllers.setLayoutParams(layoutParams);
        setControllers(controllers);
        addView(controllers);
    }

    private void setControllers(View root) {
        currentSpeedTextView = root.findViewById(R.id.speedCurrent);
        currentSpeedTextView.setOnClickListener(currentSpeedListener);
        root.findViewById(R.id.speedMinus).setOnClickListener(speedDecreaseListener);
        root.findViewById(R.id.speedPlus).setOnClickListener(speedIncreaseListener);
        printCurrentSpeed();
    }

    private OnClickListener currentSpeedListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            speedController.reset();
            printCurrentSpeed();
        }
    };

    private OnClickListener speedDecreaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            speedController.decrease();
            printCurrentSpeed();
        }
    };

    private OnClickListener speedIncreaseListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            speedController.increase();
            printCurrentSpeed();
        }
    };

    private void printCurrentSpeed() {
        if (speedController != null)
            currentSpeedTextView.setText(String.format(Locale.ROOT, "%.1fx", speedController.getSpeed()));
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
