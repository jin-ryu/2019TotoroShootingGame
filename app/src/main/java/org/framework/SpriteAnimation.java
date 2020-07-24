package org.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteAnimation extends GraphicObject {
    private Rect m_rect;
    private int m_fps;
    private int m_iFrames;

    private int m_currentFrame;
    private int m_spriteWidth;
    private int m_spriteHeight;
    private long m_frameTimer;

    public SpriteAnimation(Bitmap bitmap){
        super(bitmap);

        m_rect = new Rect(0,0,0,0);
        m_frameTimer = 0;
        m_currentFrame = 0;
    }

    public void initSpriteData(int _width, int _height, int _fps, int iFrame) {
        m_spriteWidth = _width;
        m_spriteHeight = _height;
        m_rect.top = 0;
        m_rect.left = 0;
        m_fps = 1000/_fps;
        m_iFrames = iFrame;
        m_rect.bottom = m_spriteHeight;
        m_rect.right = m_spriteWidth;
    }

    public void Draw(Canvas canvas){
        Rect dest = new Rect(m_x,m_y,m_x + m_spriteWidth,m_y+m_spriteHeight);
        canvas.drawBitmap(m_bitmap,m_rect,dest,null);
    }

    public void Update(long gameTime){
        if(gameTime > m_frameTimer + m_fps){
            m_frameTimer = gameTime;
            m_currentFrame += 1;

            if(m_currentFrame >= m_iFrames) m_currentFrame = 0;
        }
        m_rect.left = m_currentFrame*m_spriteWidth;
        m_rect.right = m_rect.left + m_spriteWidth;
    }
}
