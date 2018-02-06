package com.kbnt.qam.mediacontroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MediaController extends View {

    public MediaController(Context context) {
        this(context, null);
    }

    public MediaController(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MediaController(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public MediaController(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
