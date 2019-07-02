package org.framework;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import org.totoroshootinggame.PopUpActivity;
import org.totoroshootinggame.R;

public class GameActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor gyroScopeSensor, accelSensor, sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        //센서
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, gyroScopeSensor,SensorManager.SENSOR_DELAY_GAME);
        gyroScopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        // AppManager에 저장
        AppManager.getInstance().setWindowManager(getWindowManager());
        AppManager.getInstance().setSensorManager(sensorManager);
        AppManager.getInstance().setGyroScopeSensor(gyroScopeSensor);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PopUpActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 리스너 등록
        sensorManager.registerListener(this, gyroScopeSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // 쓰레드 멈춤
        AppManager.getInstance().getGameView().stopGameViewThread();

        // 리스너 해제
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // 정확도에 대한 메소드 호출
    }
}
