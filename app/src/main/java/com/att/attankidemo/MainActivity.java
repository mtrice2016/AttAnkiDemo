package com.att.attankidemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    public static final String MAIN_ACTIVITY_TO_OPEN = "com.att.attankidemo.Main_Activity_To_Open";


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
        Intent intent = new Intent(this, Setup2Activity.class);
        intent.putExtra(MAIN_ACTIVITY_TO_OPEN, "2");
        startActivity(intent);
    }

    public void demo3(View view) {
        Intent intent = new Intent(this, Setup2Activity.class);
        intent.putExtra(MAIN_ACTIVITY_TO_OPEN, "3");
        startActivity(intent);
    }


}


