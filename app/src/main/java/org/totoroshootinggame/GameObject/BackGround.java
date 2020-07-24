package org.totoroshootinggame.GameObject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.totoroshootinggame.R;

public class BackGround extends GraphicObject {
    private Bitmap backGround2 = AppManager.getInstance().getBitmap(R.drawable.background2);
    private Bitmap backGround = AppManager.getInstance().getBitmap(R.drawable.background);

    // background flag
    private final int NORMAL_BACKGROUND = 0;
    private final int BOSS_BACKGROUND = 1;

    public BackGround(int backType) {
        super( null);
        // 바탕화면
        if(backType == NORMAL_BACKGROUND){
            m_bitmap = Bitmap.createScaledBitmap(backGround2, AppManager.getInstance( ).getDisplayWidth(),
                    backGround.getHeight(), false);
        }
        else if(backType == BOSS_BACKGROUND){
            m_bitmap = Bitmap.createScaledBitmap(backGround, AppManager.getInstance( ).getDisplayWidth(),
                    backGround.getHeight(), false);
        }

        //setPosition(0, (int) scroll);

    }

    public void Update(long gameTime){

    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap( m_bitmap, m_x, m_y, null);
    }
}
