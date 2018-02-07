package com.kbnt.qam.playersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kbnt.qam.mediacontroller.MediaController;

public class MainActivity extends AppCompatActivity {

    private MediaController mediaController;
    private Toast toast;
    private int brightness = 0;
    private int volume = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaController = findViewById(R.id.mediaController);
        mediaController.setControlListener(controlListener);
    }

    private MediaController.ControlListener controlListener = new MediaController.ControlListener() {
        @Override
        public void play() {
            showMessage("play");
        }

        @Override
        public void pause() {
            showMessage("pause");
        }

        @Override
        public void doubleTap() {
            showMessage("doubleTap");
        }

        @Override
        public void longClick() {
            showMessage("longClick");
        }

        @Override
        public void brightness(int value) {
            brightness = Math.max(0, Math.min(brightness + value, 100));
            showMessage("brightness " + brightness);
        }

        @Override
        public void volume(int value) {
            volume = Math.max(0, Math.min(volume + value, 100));
            showMessage("volume " + volume);
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
