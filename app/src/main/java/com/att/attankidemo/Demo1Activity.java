package com.att.attankidemo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Demo1Activity extends Activity {

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        queue = Volley.newRequestQueue(this);
    }


    public void startDemo(View view) {
        queue.add(AnkiRequests.setLights(15,0,0));
        queue.add(AnkiRequests.setLights("Thermo", 15,9,0));
        queue.add(AnkiRequests.setLights("Guardian", 0,15,0));
        queue.add(AnkiRequests.setSpeed(500));
        queue.add(AnkiRequests.setSpeed("Thermo", 570));
        queue.add(AnkiRequests.setSpeed("Guardian", 645));

        try {

            //sleep 5 seconds
            Thread.sleep(8000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queue.add(AnkiRequests.setSpeed("Thermo", 810));
        queue.add(AnkiRequests.setSpeed("Guardian", 910));

        try {

            //sleep 5 seconds
            Thread.sleep(8000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        queue.add(AnkiRequests.setSpeed("Guardian", 1250));
    }

    public void endDemo(View view) {
        StringRequest request = AnkiRequests.setSpeed(0);
        queue.add(request);
    }
}
