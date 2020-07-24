package org.totoroshootinggame.Enemy;


import android.graphics.Bitmap;
import android.graphics.Rect;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.totoroshootinggame.R;

public class Boss extends GraphicObject {

    int hp;
    float speed;
    IBossState nowState;
    public Rect m_BoundBox = new Rect();

    public Boss(Bitmap bitmap) {
        super (Bitmap.createScaledBitmap(bitmap, AppManager.getInstance().getDisplayWidth()-400,400,true));
        hp = 30;
        speed = 3.0f;
        AppManager.getInstance().bossState1 = new BossPattern1(this);
        nowState = AppManager.getInstance().bossState1;
        setPosition(0,AppManager.getInstance().topBar);
    }

    public void update()
    {
        nowState.Attack();
        if(nowState.isChangeTime()) nowState = nowState.GetAnotherState();
        m_BoundBox .set( m_x, m_y, m_x + getBitmapWidth(), m_y + getBitmapHeight());
    }

    public void movePosition(int d_x, int d_y)
    {
        setPosition(getX() + d_x , getY() + d_y);
        if(getY() <AppManager.getInstance().topBar) setPosition(getX(), AppManager.getInstance().topBar);
        if(getX() < 0) setPosition( 0, getY());

    }

    public void SetSpeed(float _speed){
        speed = _speed;
    }

    public void destroyBoss(){
        --hp;

        if(hp == 10)
            m_bitmap = Bitmap.createScaledBitmap(AppManager.getInstance( ).getBitmap(R.drawable.boss2), AppManager.getInstance().getDisplayWidth()-400,400,true);
    }

    public int getBossHp()
    {
        return hp;
    }
}

