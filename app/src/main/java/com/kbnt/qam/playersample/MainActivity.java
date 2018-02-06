package com.kbnt.qam.playersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kbnt.qam.mediacontroller.MediaController;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaController = findViewById(R.id.mediaController);
        mediaController.setControlListener(new MediaController.ControlListener() {
            @Override
            public void play() {
                Log.e(TAG, "play");
            }

            @Override
            public void pause() {
                Log.e(TAG, "pause");
            }
        });
    }
}
