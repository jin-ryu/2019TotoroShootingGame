package org.totoroshootinggame.Player;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.totoroshootinggame.R;

public class Player extends GraphicObject implements SensorEventListener {
    public Rect m_BoundBox = new Rect();
    private int m_Life = 3; //목숨 세개

    // player image
    private Bitmap normal = AppManager.getInstance().getBitmap(R.drawable.totoro_normal);
    private Bitmap unbeatable = AppManager.getInstance().getBitmap(R.drawable.totoro_unbeatable);
    private Bitmap sheild = AppManager.getInstance().getBitmap(R.drawable.totoro_shield);

    // player flag
    private int playerType = 0;
    private int NORMAL_PLAYER = 0;  // 일반 상태
    private int UNBEATABLE_PLAYER = 1;   // 무적 상태
    private int SHIELD_PLAYER = 2;  // 쉴드 상태

    // sensor
    private SensorManager sensorManager;
    private Sensor gyroSensor;

    public void addLife() { m_Life++; }

    public void destroyPlayer() { m_Life--; }

    public int getLife() { return m_Life; }

    public Player(int playerType) {
        super(null);
        this.playerType = playerType;   // 현재 플레이어 상태 저장

        if (playerType == NORMAL_PLAYER) {
            m_bitmap = Bitmap.createScaledBitmap(normal, 200, 300, false);
        } else if (playerType == UNBEATABLE_PLAYER) {
            m_bitmap = Bitmap.createScaledBitmap(unbeatable, 500, 500, false);
        } else if (playerType == SHIELD_PLAYER) {
            m_bitmap = Bitmap.createScaledBitmap(sheild, 400, 500, false);
        }

        // 위치 조정
        setPosition(AppManager.getInstance().getDisplayWidth() / 2 - this.getBitmapWidth() / 2,
                AppManager.getInstance().getDisplayHeight() - this.getBitmapHeight() - 100);

        // 센서 가져오기

    }

    public void Update(long gameTime) {
        m_BoundBox.set(m_x, m_y, m_x + this.getBitmapWidth(), m_y + this.getBitmapHeight());
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
