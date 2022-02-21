package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relativeLayout;
    TextView textView;
    Fragment fragment;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.AXIS_X);

    }
    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            String str = String.valueOf(sensorEvent.values[0]);
            double db = (Double.valueOf(str))*500;
            Log.e("XXXX", ""+ (int) db);
            if (db>500 && db<10000) {
//                getWindow().getDecorView().setBackgroundColor(Color.parseColor("#" + Integer.toHexString((int) db) + Integer.toHexString((int) db)));
                getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                textView.setTextColor(Color.WHITE);
            } else if (db > 10000 && db < 30000) {
                getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                textView.setTextColor(Color.WHITE);
            } else if (db > 30000) {
                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
            }
            Log.e("XXXX", ""+ db);
            textView.setText(db+"");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}