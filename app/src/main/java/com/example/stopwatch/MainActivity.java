package com.example.stopwatch;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.net.ssl.SNIHostName;

public class MainActivity extends AppCompatActivity{
    boolean running;
    TextView textView;
    int seconds = 0;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.startButton);
        //handle configuration changes
           if(savedInstanceState != null) {
               seconds = savedInstanceState.getInt("seconds");
               running = savedInstanceState.getBoolean("running");
           }
        runStopwatch();
    }

    public void onClickStart(View v) {
        running = true;
    }

    public void onClickPause(View v) {
        running = false;
    }

    public void onClickReset(View v) {
        running = false;
        seconds = 0;
    }

    public void runStopwatch() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = seconds / 60;
                int secs = seconds % 60;
                textView = (TextView) findViewById(R.id.textView1);
                String timer = String.format("%02d:%02d:%02d", hours, minutes, secs);
                textView.setText(timer);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
          savedInstanceState.putInt("seconds",seconds);
          savedInstanceState.putBoolean("running",running);
          super.onSaveInstanceState(savedInstanceState);
    }


}