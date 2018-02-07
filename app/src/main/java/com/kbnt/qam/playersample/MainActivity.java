package com.kbnt.qam.playersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kbnt.qam.mediacontroller.MediaController;

public class MainActivity extends AppCompatActivity {

    private MediaController mediaController;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaController = findViewById(R.id.mediaController);
        mediaController.setControlListener(new MediaController.ControlListener() {
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
            public void scroll() {
                showMessage("scroll");
            }
        });
    }

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
