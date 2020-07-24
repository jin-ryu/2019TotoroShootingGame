package org.totoroshootinggame.Enemy;

import android.graphics.Bitmap;
import android.graphics.Rect;

import org.framework.AppManager;
import org.framework.GraphicObject;

public class Enemy extends GraphicObject {

    public Rect m_BoundBox = new Rect();

    protected int hp;
    protected float speed;
    public static final int MOVE_PATTERN_1 = 0;
    public static final int MOVE_PATTERN_2 = 1;
    public static final int MOVE_PATTERN_3 = 2;

    public int movetype;

    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT = 1;
    public int state = STATE_NORMAL;

    int enemyType;

    long LastShoot = System.currentTimeMillis();

    public Enemy(Bitmap bitmap) {
        super(bitmap);
    }


    public void Update(long gameTime) {
        Move();
    }

    void Move() {
        // 움직이는 로직
        if (movetype == MOVE_PATTERN_1) {
            if (m_y <= 500) m_y += speed;
            else m_y += speed * 2;
        } else if (movetype == MOVE_PATTERN_2) {
            if (m_y <= 500) m_y += speed; // 중간지점까지 일자로 이동
            else {
                m_x += speed; // 중감지점이후 대각선 이동
                m_y += speed; }
        } else if (movetype == MOVE_PATTERN_3) {
            if (m_y <= 500) m_y += speed;
            else { // 중간지점까지 일자로 이동 // 중감지점이후 대각선 이동
                m_x -= speed;
                m_y += speed;
            }
        }

        if(m_x < 0 )
            movetype = MOVE_PATTERN_2;
        else if(m_x + getBitmapWidth() >= AppManager.getInstance().getDisplayWidth())
            movetype = MOVE_PATTERN_3;

        if (m_y > AppManager.getInstance().getDisplayHeight())
            state = STATE_OUT;
    }

    public int getEnemyType()
    {
        return enemyType;
    }
}