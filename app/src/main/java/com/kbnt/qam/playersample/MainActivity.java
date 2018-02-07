package com.kbnt.qam.playersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kbnt.qam.mediacontroller.MediaController;

public class MainActivity extends AppCompatActivity {

    private MediaController mediaController;

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
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
