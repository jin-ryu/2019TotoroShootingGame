package org.totoroshootinggame.GameObject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.totoroshootinggame.R;

public class CloudLayer extends GraphicObject {
    private static Bitmap layer = AppManager.getInstance().getBitmap(R.drawable.cloud_layer);

    private final float SCORLL_SPEED = 10f;
    private float scroll = - layer.getHeight() + 480;

    public CloudLayer() {
        super(Bitmap.createScaledBitmap(layer, AppManager.getInstance().getDisplayWidth(),
                layer.getHeight(), false));

        setPosition(0, (int) scroll);

    }

    public void Update(long gameTime){
        // 구름 레이어 스크롤
        scroll = scroll + SCORLL_SPEED;
        if( scroll >= 0 )
            scroll = -3350;
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawBitmap( m_bitmap, m_x, scroll, null);
    }
}
