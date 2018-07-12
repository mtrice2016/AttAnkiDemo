package com.att.attankidemo;

import android.widget.Button;

public class Utils {

    public static void enableButton(Button b) {
        b.setAlpha(1f);
        b.setClickable(true);
    }

    public static void disableButton(Button b) {
        b.setAlpha(.5f);
        b.setClickable(false);
    }

    public static boolean sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
