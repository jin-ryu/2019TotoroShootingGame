package org.totoroshootinggame.Player;

import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import org.framework.AppManager;
import org.totoroshootinggame.GameObject.Item;
import org.totoroshootinggame.R;

public class ShieldPlayer extends Player  {

    private Bitmap sheild = AppManager.getInstance().getBitmap(R.drawable.totoro_shield);
    protected long ChangeTime = System.currentTimeMillis( );

    public ShieldPlayer()
    {
        m_bitmap = Bitmap.createScaledBitmap(sheild, 200, 300, false);
    }

    @Override
    public boolean destroyPlayer() {
        return false;
    }

    @Override
    public boolean isChangeType() {  // 현재 타입 반환
        if(System.currentTimeMillis( ) > ChangeTime + 3000) return true;
        return false;
    }

    @Override
    public int playerType() {
        return Item.SHIELD;
    }  //플레이어 타입

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
      super.onSensorChanged(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}