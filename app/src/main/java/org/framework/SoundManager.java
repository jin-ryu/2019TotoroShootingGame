package org.framework;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import org.totoroshootinggame.R;

public class SoundManager {

    MediaPlayer m_Sound_BackGround ;
    SoundPool m_SoundPool;
    int m_Sound;

    private static SoundManager s_instance;

    public static SoundManager getInstance(){
        if(s_instance == null) s_instance = new SoundManager();
        return s_instance;
    }

    private SoundManager()
    {
        m_SoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
    }

    public void playBackground(Context context, int background)
    {
        m_Sound_BackGround = MediaPlayer.create(context, background);
        m_Sound_BackGround.start();

    }

    public void playSoundEffect(Context context, int sound_id, final float volume)
    {
        m_Sound = m_SoundPool.load(context,sound_id,1);
        //m_SoundPool.play(m_Sound, 1, 1, 0, 0, 1);
        m_SoundPool.setOnLoadCompleteListener (new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int soundId, int status) {
                m_SoundPool.play(m_Sound, volume, volume, 0, 0, 1f);
            }
        });
    }

    public void pause()
    {
        m_Sound_BackGround.pause();
    }

    public void start()
    {
        m_Sound_BackGround.start();
    }

    public void release()
    {
        m_Sound_BackGround.release();
    }
}
