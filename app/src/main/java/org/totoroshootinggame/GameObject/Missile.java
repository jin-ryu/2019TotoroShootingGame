package org.totoroshootinggame.GameObject;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.totoroshootinggame.R;

public class Missile extends GraphicObject {
    public static final int STATE_NORMAL= 0;
    public static final int STATE_OUT= 1;
    public int state = STATE_NORMAL;

    private static Bitmap missileImage = Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.missile),
            100, 100,false);
    public Rect m_BoundBox = new Rect();

    public Missile() {
        super(missileImage);
    }

    public void Update(){
        m_y -= 10;
        if( m_y < 50 ) {
            state = STATE_OUT;
        }

        // 충돌 박스 값 변경
        m_BoundBox.set(m_x, m_y, m_x+this.getBitmapWidth(), m_y+this.getBitmapHeight());
    }
}
