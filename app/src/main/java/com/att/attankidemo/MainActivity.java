package com.att.attankidemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void demo1(View view) {
        Intent intent = new Intent(this, Setup1Activity.class);
        startActivity(intent);
    }

    public void demo2(View view) {
        Intent intent = new Intent(this, Demo1Activity.class);
        startActivity(intent);
    }

    public void demo3(View view) {
        //start setup
    }


}


