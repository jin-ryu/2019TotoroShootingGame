package org.totoroshootinggame.Enemy;

import android.graphics.Bitmap;

import org.framework.AppManager;
import org.totoroshootinggame.R;


public class Enemy_1 extends Enemy {
    public Enemy_1(Bitmap bitmap) {
        super(bitmap);
    }

    public Enemy_1( ) {
        super (Bitmap.createScaledBitmap(AppManager.getInstance( ).getBitmap(R.drawable. enemy1),160,130,true));

        hp = 10;
        speed = 4f;
        enemyType = 1;

        movetype = Enemy.MOVE_PATTERN_2;
    }

    @Override
    public void Update(long gameTime)
    {
        super.Update(gameTime);
        m_BoundBox .set( m_x, m_y, m_x + 62, m_y + 104);

    }

}
