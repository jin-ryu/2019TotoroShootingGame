package org.totoroshootinggame.GameObject;

import android.graphics.Bitmap;

import org.framework.AppManager;
import org.totoroshootinggame.Enemy.Enemy;
import org.totoroshootinggame.R;

public class Item extends Enemy {

    public static final int HEART = 0;
    public static final int SHIELD = 1;
    public static final int UNBEATABLE = 2;
    public static final int CLOUD = 3;

    int itemType;

    private Bitmap item1Image = AppManager.getInstance().getBitmap(R.drawable.item1);
    private Bitmap item2Image = AppManager.getInstance().getBitmap(R.drawable.item2);
    private Bitmap item3Image = AppManager.getInstance().getBitmap(R.drawable.item3);
    private Bitmap item4Image = AppManager.getInstance().getBitmap(R.drawable.item7);

    public Item(int itemType) {
        super( null);
        switch (itemType){
            case 0:
                m_bitmap = Bitmap.createScaledBitmap(item1Image,160,130,true);
                break;
            case 1:
                m_bitmap = Bitmap.createScaledBitmap(item2Image,130,170,true);
                break;
            case 2:
                m_bitmap = Bitmap.createScaledBitmap(item3Image,170,140,true);
                break;
            case 3:
                m_bitmap = Bitmap.createScaledBitmap(item4Image,160,100,true);
                break;
        }
        this.itemType = itemType;
        movetype = Enemy.MOVE_PATTERN_1;  // 움직이는 패턴 아래로
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
