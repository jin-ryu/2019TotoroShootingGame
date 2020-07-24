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
    public boolean isChangeType() {
        if(System.currentTimeMillis( ) > ChangeTime + 3000) return true;
        return false;
    }

    @Override
    public int playerType() {
        return Item.SHIELD;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float roll = sensorEvent.values[2];
        int rightBound  = AppManager.getInstance().getDisplayWidth() - m_bitmap.getWidth();
        int leftBound = 0;

        if (roll > 10 && m_x - 15 > leftBound) {
            setPosition(m_x - 15, m_y); // 왼쪽 이동
        } else if(roll < -10 && m_x + 15 < rightBound){
            setPosition(m_x + 15, m_y); // 오른쪽 이동
        }
        Log.d("자이로 실드", m_x + " " + m_y);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}