package org.totoroshootinggame.Player;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.view.MotionEvent;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.totoroshootinggame.GameObject.Item;
import org.totoroshootinggame.GameObject.Missile;
import org.totoroshootinggame.R;

import java.util.ArrayList;

public class Player extends GraphicObject implements SensorEventListener, IPlayerState {
    public Rect m_BoundBox = new Rect();
    private int m_Life = 3; //목숨 세개
    private int playerPoint;
    long lastDestory;
    long lastMissile;

    // player image
    private Bitmap normal = AppManager.getInstance().getBitmap(R.drawable.totoro_normal);


    public Player() {
        super(null);
        playerPoint = 0;
        m_bitmap = Bitmap.createScaledBitmap(normal, 200, 300, false);
        setPlayerImage();   // 상태에 따른 이미지 적용
        // 위치 조정
        lastDestory = System.currentTimeMillis( );
        lastMissile  = System.currentTimeMillis( );
        setPosition(AppManager.getInstance().getDisplayWidth() / 2 - this.getBitmapWidth() / 2,
                AppManager.getInstance().getDisplayHeight() - this.getBitmapHeight() - 100);
    }

    public void setPlayerImage(){
        // 플레이어 이미지 적용
        m_bitmap = Bitmap.createScaledBitmap(normal, 200, 300, false);
    }

    @Override
    public void addLife() { m_Life++; }

    @Override
    public boolean destroyPlayer() {

        if(System.currentTimeMillis() > lastDestory + 500) {
            lastDestory = System.currentTimeMillis();
            m_Life--;
            return true;
        }
        return  false;
    }

    @Override
    public int getLife() { return m_Life; }

    @Override
    public void addPoint(){ playerPoint++;}

    @Override
    public int getPoint(){return playerPoint;}

    public void Update() {
        // 충돌 바운드 조정
        m_BoundBox.set(m_x, m_y, m_x + this.getBitmapWidth(), m_y + this.getBitmapHeight());
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
        Log.d("자이로 플레", m_x + " " + m_y);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onTouchEvent(MotionEvent event, ArrayList<Missile> m_pmslist) {

        int x = getX();
        int y = getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(System.currentTimeMillis() < lastMissile + 333) return;
            Missile missile = new Missile();
            missile.setPosition(x + missile.getBitmapWidth()/2, y);
            m_pmslist.add(missile);
        }

        lastMissile = System.currentTimeMillis();
    }

    @Override
    public boolean isChangeType() {
        return false;
    }

    @Override
    public int playerType() {
        return Item.STATE_NORMAL;
    }
}