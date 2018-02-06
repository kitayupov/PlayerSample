package com.kbnt.qam.playersample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kbnt.qam.mediacontroller.MediaController;

public class MainActivity extends AppCompatActivity {

    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaController = findViewById(R.id.mediaController);
    }
}
