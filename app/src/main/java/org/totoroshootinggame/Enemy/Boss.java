package org.totoroshootinggame.Enemy;


import android.graphics.Bitmap;
import android.graphics.Rect;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.totoroshootinggame.R;

public class Boss extends GraphicObject {  //Boss

    int hp;
    float speed;
    IBossState nowState;
    public Rect m_BoundBox = new Rect();

    public Boss(Bitmap bitmap) {
        super (Bitmap.createScaledBitmap(bitmap, AppManager.getInstance().getDisplayWidth()-400,400,true));
        hp = 30;
        speed = 3.0f;
        nowState = new BossPattern1(this);
        AppManager.getInstance().bossState1 = nowState;
        setPosition(0,AppManager.getInstance().topBar);
    }

    public void update()
    {
        nowState.Attack();
        if(nowState.isChangeTime()) nowState = nowState.GetAnotherState();
        m_BoundBox .set( m_x, m_y, m_x + getBitmapWidth(), m_y + getBitmapHeight());
    }

    public void movePosition(int d_x, int d_y) //간격만큼 좌표이동 메서드.
    {
        setPosition(getX() + d_x , getY() + d_y);
        if(getY() < AppManager.getInstance().topBar) setPosition(getX(), AppManager.getInstance().topBar);
        if(getX() < 0) setPosition( 0, getY());

    }

    public void SetSpeed(float _speed){
        speed = _speed;
    }  //움직이는 스피드 변화 메서드

    public void destroyBoss(){   // 피 깎기.
        --hp;

        if(hp == 10)  // 피가 10일 경우 이미지 변경
            m_bitmap = Bitmap.createScaledBitmap(AppManager.getInstance( ).getBitmap(R.drawable.boss2),
                    AppManager.getInstance().getDisplayWidth()-400,400,true);
    }

    public int getBossHp()
    {
        return hp;
    }
}

