package com.kbnt.qam.mediacontroller;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

class VolumeController {

    private static final float MIN_VOLUME = 0.0F;
    private final float MAX_VOLUME;

    private final AudioManager audioManager;
    private float volume = 0;

    VolumeController(Activity activity) {
        audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        MAX_VOLUME = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    void setVolume(float value) {
        volume = Math.max(MIN_VOLUME, Math.min(volume + value, MAX_VOLUME));
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, (int) volume, 0);
    }
}
