package com.kbnt.qam.mediacontroller;

import android.app.Activity;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

class BrightnessController {

    private static final float MIN_VALUE = 0.0F;
    private static final float MAX_VALUE = 100.0F;

    private final Activity context;
    private float brightness;

    BrightnessController(Activity context) {
        this.context = context;
        try {
            brightness = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS) / 255.0F * MAX_VALUE;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    void setBrightness(float value) {
        brightness = Math.max(MIN_VALUE, Math.min(brightness + value * MAX_VALUE, MAX_VALUE));
        final Window window = context.getWindow();
        final WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = brightness / MAX_VALUE;
        window.setAttributes(layoutParams);
    }
}
