package tvz.gymplanner.pedometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import tvz.gymplanner.R;

/**
 * Created by Filip on 30.5.2015..
 */
public class PedometarActivity extends ActionBarActivity implements SensorEventListener {

    private Sensor sensorStep;
    private SensorManager sensorManager;
    private TextView prikazKoraka;
    private float staro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometar);
        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorStep=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        prikazKoraka=(TextView) findViewById(R.id.tex_pedometar);
        prikazKoraka.setText("Koraka: 0");



    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

         sensorStep = sensorEvent.sensor;
        staro=sensorEvent.values[0];


            prikazKoraka.setText("Koraka : " + (sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }



    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this, sensorStep);

    }
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, sensorStep);

    }
    @Override
    protected void onResume() {
        super.onResume();
		sensorManager.registerListener(this, sensorStep, SensorManager.SENSOR_DELAY_FASTEST);


    }
}
