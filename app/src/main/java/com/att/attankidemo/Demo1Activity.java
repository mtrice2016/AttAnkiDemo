package com.att.attankidemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Demo1Activity extends Activity {

    private RequestQueue queue;
    private String innerCar;
    private String middleCar;
    private String outerCar;

    Button startButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);

        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        Intent intent = getIntent();
        innerCar = intent.getStringExtra(Setup1Activity.DEMO_1_INNER_CAR);
        middleCar = intent.getStringExtra(Setup1Activity.DEMO_1_MIDDLE_CAR);
        outerCar = intent.getStringExtra(Setup1Activity.DEMO_1_OUTER_CAR);

        queue = Volley.newRequestQueue(this);

        UiUtils.disableButton(stopButton);
    }


    public void startDemo(View view) {
        UiUtils.disableButton(startButton);
        UiUtils.disableButton(stopButton);

        queue.add(AnkiRequests.setLights(innerCar,15,0,0));
        queue.add(AnkiRequests.setLights(middleCar, 14,9,0));
        queue.add(AnkiRequests.setLights(outerCar, 0,15,0));

        queue.add(AnkiRequests.setSpeed(outerCar, 645));
        queue.add(AnkiRequests.setSpeed(middleCar, 570));
        queue.add(AnkiRequests.setSpeed(innerCar, 500));

        sleep(8);

        queue.add(AnkiRequests.setSpeed(outerCar, 910));
        queue.add(AnkiRequests.setSpeed(middleCar, 810));

        sleep(8);

        queue.add(AnkiRequests.setSpeed(outerCar, 1250));

        UiUtils.enableButton(stopButton);
    }

    public void endDemo(View view) {
        StringRequest request = AnkiRequests.setSpeed(0);
        queue.add(request);

        UiUtils.disableButton(stopButton);
        UiUtils.enableButton(startButton);
    }

    public void forceStop(View view) {
        queue.add(AnkiRequests.setSpeed(0));
        UiUtils.enableButton(startButton);
    }



    private boolean sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
