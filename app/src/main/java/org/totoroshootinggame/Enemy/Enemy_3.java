package org.totoroshootinggame.Enemy;

import android.graphics.Bitmap;

import org.framework.AppManager;
import org.totoroshootinggame.R;

public class Enemy_3 extends Enemy {
    public Enemy_3(Bitmap bitmap) {
        super(bitmap);
    }

    public Enemy_3( ) {
        super (Bitmap.createScaledBitmap(AppManager.getInstance( ).getBitmap(R.drawable. enemy4),160,130,true));
        hp = 30;
        speed = 4f;
        enemyType = 3;

        movetype = Enemy.MOVE_PATTERN_2;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        m_BoundBox .set( m_x, m_y, m_x + 62, m_y + 104);
    }
}
