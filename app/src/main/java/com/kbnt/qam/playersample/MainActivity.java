package com.kbnt.qam.playersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kbnt.qam.mediacontroller.MediaController;

public class MainActivity extends AppCompatActivity {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MediaController mediaController = findViewById(R.id.layout);
        mediaController.setControlCallback(controlCallback);
    }

    private MediaController.ControlCallback controlCallback = new MediaController.ControlCallback() {
        @Override
        public void play() {
            showMessage("play");
        }

        @Override
        public void pause() {
            showMessage("pause");
        }

        @Override
        public void longClick() {
            showMessage("longClick");
        }

        @Override
        public void jumpLeft() {
            showMessage("jumpLeft");
        }

        @Override
        public void jumpRight() {
            showMessage("jumpRight");
        }

        @Override
        public void setSpeed(float value) {
            showMessage("setSpeed: " + value);
        }
    };

    private void showMessage(String message) {
        if (toast == null) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            toast.setText(message);
            toast.show();
        }
    }
}
