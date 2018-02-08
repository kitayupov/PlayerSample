package com.kbnt.qam.mediacontroller;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

class VolumeController {

    private static final float MIN_VALUE = 0.0F;
    private final float MAX_VALUE;

    private final AudioManager audioManager;
    private float volume;

    VolumeController(Activity activity) {
        audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        MAX_VALUE = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    void setVolume(float value) {
        volume = Math.max(MIN_VALUE, Math.min(volume + value * MAX_VALUE, MAX_VALUE));
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) volume, 0);
    }
}
