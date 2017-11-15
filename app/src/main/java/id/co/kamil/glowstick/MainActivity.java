package id.co.kamil.glowstick;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor sensor;
    private int red=100;
    private int green=10;
    private int blue=200;
    private TextView txtLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLog = (TextView) findViewById(R.id.txtlog);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        red = Math.round(event.values[0]*20f);
        green = Math.round(event.values[1]*20f);
        blue = Math.round(event.values[2]*20f);


        Log.i(TAG,"R : " +red);
        Log.i(TAG,"G : " +green);
        Log.i(TAG,"B : " +blue);

        if (red>255) red = 255;
        if (green>255) green = 255;
        if (blue>255) blue = 255;

        if (red<0) red = 0;
        if (green<0) green = 0;
        if (blue<0) blue = 0;

        txtLog.setText(String.format("R : %s G : %s B : %s",red,green,blue));

        final int color = Color.rgb(red,green,blue);
        View rootView = getWindow().getDecorView();
        rootView.setBackgroundColor(color);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
