package org.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GraphicObject {
    protected Bitmap m_bitmap;
    protected int m_x, m_y;
//jhuhuih
    public GraphicObject(Bitmap m_bitmap) {
        this.m_bitmap = m_bitmap;
        m_x = m_y = 0;
    }

    // 좌표 설정
    public void setPosition(int x, int y){
        m_x = x;
        m_y = y;
    }

    // 이미지 그림
    public void Draw(Canvas canvas){
        canvas.drawBitmap(m_bitmap, m_x, m_y, null);
    }

    // X,Y 각 좌표 변환
    public int getX(){ return m_x; }
    public int getY(){ return m_y; }

}
