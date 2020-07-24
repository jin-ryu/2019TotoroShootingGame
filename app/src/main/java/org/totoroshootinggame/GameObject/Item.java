package org.totoroshootinggame.GameObject;

import android.graphics.Bitmap;

import org.totoroshootinggame.Enemy.Enemy;

public class Item extends Enemy {

    public static final int HEART = 0;
    public static final int SHIELD = 1;
    public static final int UNBEATABLE = 2;
    public static final int CLOUD = 3;

    int itemType;

    public Item(Bitmap bitmap, int itemNumber) {
        super(Bitmap.createScaledBitmap(bitmap,160,130,true));
        movetype = Enemy.MOVE_PATTERN_1;
        itemType = itemNumber;
        speed = 4f;
    }

    @Override
    public void Update(long gameTime) {
        super.Update(gameTime);
        if(speed > 4f) speed = 4f;
        m_BoundBox .set( m_x, m_y, m_x + 160, m_y + 160);
    }

    public int getItemType()
    {
        return itemType;
    }
}
