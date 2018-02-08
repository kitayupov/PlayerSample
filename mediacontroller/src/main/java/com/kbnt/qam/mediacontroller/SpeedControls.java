package com.kbnt.qam.mediacontroller;

import android.view.View;
import android.widget.TextView;

import java.util.Locale;

class SpeedControls {

    private SpeedController speedController;
    private TextView currentSpeedTextView;

    SpeedControls(View root) {
        speedController = new SpeedController();
        initialize(root);
    }

    void setControlCallback(MediaController.ControlCallback controlCallback) {
        speedController.setControlCallback(controlCallback);
    }

    private void initialize(View root) {
        currentSpeedTextView = root.findViewById(R.id.speedCurrent);
        currentSpeedTextView.setOnClickListener(currentSpeedListener);
        root.findViewById(R.id.speedMinus).setOnClickListener(speedDecreaseListener);
        root.findViewById(R.id.speedPlus).setOnClickListener(speedIncreaseListener);
        printCurrentSpeed();
    }

    private View.OnClickListener currentSpeedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            speedController.reset();
            printCurrentSpeed();
        }
    };

    private View.OnClickListener speedDecreaseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            speedController.decrease();
            printCurrentSpeed();
        }
    };

    private View.OnClickListener speedIncreaseListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            speedController.increase();
            printCurrentSpeed();
        }
    };

    private void printCurrentSpeed() {
        if (speedController != null) {
            currentSpeedTextView.setText(String.format(Locale.ROOT, "%.1fx", speedController.getSpeed()));
        }
    }
}
