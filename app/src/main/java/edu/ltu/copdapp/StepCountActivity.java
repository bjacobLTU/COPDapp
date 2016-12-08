package edu.ltu.copdapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StepCountActivity extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    TextView tv_steps;
    //Number of seconds displayed on the stopwatch.
    private int seconds = 0;
    private boolean running;
    private boolean resetStep;
    private float initialstep = 0.0f;
    private long timestamp = 0;
    private long initialtime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        setContentView(R.layout.activity_step_count);
        tv_steps = (TextView) findViewById(R.id.tracksteps);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        running = false;
        resetStep = false;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        //sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!resetStep) {
            initialstep = event.values[0];
            //initialtime = event.timestamp / 1000000000;
            resetStep = true;
        }
        if (running) {
            // Time is in nanoseconds, convert to millis
            timestamp = event.timestamp / 1000000000;
            //tv_time.setText(String.valueOf(timestamp - initialtime));
            tv_steps.setText(String.valueOf(event.values[0] - (int) initialstep));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void startCounter(View v) {
        running = true;
        resetStep = false;
        tv_steps.setText(String.valueOf("Ready"));
        seconds = 0;
        //runTimer(v);
    }

    public void stopCounter(View v) {
        running = false;
        Toast.makeText(this, "Eat better and exercise MORE!!!", Toast.LENGTH_SHORT).show();
    }

    private void runTimer(View view) {
        final TextView timeView = (TextView) view.findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
