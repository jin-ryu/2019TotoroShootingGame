package org.totoroshootinggame.Player;

import android.view.MotionEvent;

import org.totoroshootinggame.GameObject.Missile;

import java.util.ArrayList;

public interface IPlayerState {

    void onTouchEvent(MotionEvent event, ArrayList<Missile> m_pmslist);

    void addLife();

    boolean destroyPlayer();

    int getLife() ;

    void addPoint();

    int getPoint();

    void Update();

    boolean isChangeType();

    int playerType();
}
