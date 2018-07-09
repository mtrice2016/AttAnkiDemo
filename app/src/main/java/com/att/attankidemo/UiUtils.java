package com.att.attankidemo;

import android.widget.Button;

public class UiUtils {

    public static void enableButton(Button b) {
        b.setAlpha(1f);
        b.setClickable(true);
    }

    public static void disableButton(Button b) {
        b.setAlpha(.5f);
        b.setClickable(false);
    }

}
