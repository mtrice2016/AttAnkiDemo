package com.att.attankidemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Demo3Activity extends AppCompatActivity {

    private RequestQueue queue;
    private String car4g;
    private String car5g;

    float multiplier4g = 1;
    float multiplier5g = 1.08f;

    int delay = 0;
    int speed5g = 0;
    int speed4g = 0;

    Timer timer;

    Button boostButton;
    int boostButtonTransparency;

    private final int SPEED_DEGRADATION = 3;
    private final int BOOST_INCREASE = 600;
    private final int LATENCY_4G = 15;
    private final int MAX_SPEED = 1200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);

        Intent intent = getIntent();
        car4g = intent.getStringExtra(Setup2Activity.DEMO_2_5G_CAR);
        car5g = intent.getStringExtra(Setup2Activity.DEMO_2_4G_CAR);

        boostButton = findViewById(R.id.boostButton);

        queue = Volley.newRequestQueue(this);
    }


    public void startDemo(View view) {
        queue.add(AnkiRequests.setLights(car4g,14,9,0));
        queue.add(AnkiRequests.setLights(car5g, 0, 15,0));

        speed4g = 800;
        speed5g = 800;

        switchToBoostScreen();

        createTimer();
        timer.scheduleAtFixedRate(new DemoTickTask(), 0, 50);
    }

    private void switchToBoostScreen() {
        setContentView(R.layout.activity_demo3_boost);
        boostButton = findViewById(R.id.boostButton);
    }

    @Override
    public void onDestroy() {
        queue.add(AnkiRequests.setSpeed(car5g, 0));
        queue.add(AnkiRequests.setSpeed(car4g, 0));
        closeTimer();
        super.onDestroy();
    }

    public void boost(View view) {
        speed5g += BOOST_INCREASE;
        delay = LATENCY_4G;

        boostButton.setAlpha(.5f);
        boostButton.setClickable(false);
        boostButtonTransparency = 50;
    }



    private void createTimer() {
        closeTimer();
        timer = new Timer();
    }

    private void closeTimer() {
        if(timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    class DemoTickTask extends TimerTask {
        int count = 0 ;

        public void run() {
            if (delay > 0 && delay-- == 1) {
                speed4g += BOOST_INCREASE;
            }

            speed4g -= SPEED_DEGRADATION;
            speed5g -= SPEED_DEGRADATION;

            speed4g = Utils.clamp(speed4g, 0, MAX_SPEED);
            speed5g = Utils.clamp(speed5g, 0, MAX_SPEED);

//            System.out.println("-------------------------------5-----" + speed5g);
//            System.out.println("-------------------------------4-----" + speed4g);
            if(count++ % 5 == 0) {
                queue.add(AnkiRequests.setSpeed(car5g, (int) (speed5g * multiplier5g)));
                queue.add(AnkiRequests.setSpeed(car4g, (int) (speed4g * multiplier4g)));
            }

            increaseButtonVisibility();
        }
    }

    private void increaseButtonVisibility() {
        if(boostButtonTransparency < 100) {
            boostButtonTransparency += 1;
            boostButtonTransparency = boostButtonTransparency > 100 ? 100 : boostButtonTransparency;

            boostButton.setAlpha(((float)boostButtonTransparency)/100);

            if(boostButtonTransparency == 100) {
                boostButton.setClickable(true);
            }
        }
    }

}
