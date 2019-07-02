package org.totoroshootinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.framework.AppManager;
import org.framework.GraphicObject;

public class BackGround extends GraphicObject {
    private Bitmap backGround2 = AppManager.getInstance().getBitmap(R.drawable.background2);
    private Bitmap backGround = AppManager.getInstance().getBitmap(R.drawable.background);
    private Bitmap layer = AppManager.getInstance().getBitmap(R.drawable.cloud_layer);
    private Bitmap m_layer;

    private final float SCORLL_SPEED = 10f;
    private float scroll = - layer.getHeight() + 480;

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

        // 구름 레이어
        m_layer = Bitmap.createScaledBitmap(layer, AppManager.getInstance().getDisplayWidth(),
                layer.getHeight(), false);

        //setPosition(0, (int) scroll);

    }

    public void Update(long gameTime){
        // 구름 레이어 스크롤
        scroll = scroll + SCORLL_SPEED;
        if( scroll >= 0 )
            scroll = -3350;
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap( m_bitmap, m_x, m_y, null);
       // canvas.drawBitmap( m_layer, m_x, scroll, null);
    }
}
