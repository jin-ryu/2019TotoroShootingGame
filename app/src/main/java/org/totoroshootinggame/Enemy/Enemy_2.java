package org.totoroshootinggame.Enemy;

import android.graphics.Bitmap;

import org.framework.AppManager;
import org.totoroshootinggame.R;

public class Enemy_2 extends Enemy {

    public Enemy_2(Bitmap bitmap) {
        super(bitmap);
    }

    public Enemy_2( ) {
        super (Bitmap.createScaledBitmap(AppManager.getInstance( ).getBitmap(R.drawable. enemy3),160,130,true));
        hp = 20;
        speed = 8f;
        enemyType = 2;

        movetype = Enemy.MOVE_PATTERN_2;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        m_BoundBox .set( m_x, m_y, m_x + 62, m_y + 104);
    }
}
