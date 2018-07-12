package com.att.attankidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;

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

    RunDemoTask task;

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

        Utils.disableButton(stopButton);
    }


    public void startDemo(View view) {
        task = new RunDemoTask();
        task.execute();
    }

    public void endDemo(View view) {
        queue.add(AnkiRequests.setSpeed(0));

        Utils.disableButton(stopButton);
        Utils.enableButton(startButton);
    }

    public void forceStop(View view) {
        if(task != null && task.getStatus() != AsyncTask.Status.FINISHED){
            task.cancel(true);
        }

        queue.add(AnkiRequests.setSpeed(0));
        Utils.enableButton(startButton);
    }



    private class RunDemoTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            if(!this.isCancelled()) {
                Utils.disableButton(startButton);
                Utils.disableButton(stopButton);
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            if(this.isCancelled()) return null;
            queue.add(AnkiRequests.setLights(innerCar,15,0,0));
            queue.add(AnkiRequests.setLights(middleCar, 14,9,0));
            queue.add(AnkiRequests.setLights(outerCar, 0,15,0));

            queue.add(AnkiRequests.setSpeed(outerCar, 645));
            queue.add(AnkiRequests.setSpeed(middleCar, 570));
            queue.add(AnkiRequests.setSpeed(innerCar, 500));

            Utils.sleep(8);
            if(this.isCancelled()) return null;

            queue.add(AnkiRequests.setSpeed(outerCar, 910));
            queue.add(AnkiRequests.setSpeed(middleCar, 810));

            Utils.sleep(8);
            if(this.isCancelled()) return null;

            queue.add(AnkiRequests.setSpeed(outerCar, 1250));

            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            if(!this.isCancelled()) {
                Utils.enableButton(stopButton);
            }
        }
    }

}
