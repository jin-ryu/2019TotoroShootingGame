package org.totoroshootinggame.Player;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import org.framework.AppManager;
import org.totoroshootinggame.GameObject.Item;
import org.totoroshootinggame.GameObject.Missile;
import org.totoroshootinggame.R;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnbeatablePlayer extends Player{

    private Bitmap unbeatable = AppManager.getInstance().getBitmap(R.drawable.totoro_unbeatable);

    protected long ChangeTime = System.currentTimeMillis( );

    public UnbeatablePlayer()
    {
        m_bitmap = Bitmap.createScaledBitmap(unbeatable, AppManager.getInstance().getDisplayWidth(), 600, false);
        setPosition(AppManager.getInstance().getDisplayWidth() / 2 - this.getBitmapWidth() / 2,
                AppManager.getInstance().getDisplayHeight() - this.getBitmapHeight());
    }

    @Override
    public boolean destroyPlayer() {
        return false;
    }  // 피가 깎이지 않음.

    @Override
    public boolean isChangeType() {  // 다시 돌아갈 시간.
        if(System.currentTimeMillis( ) > ChangeTime + 3000) return true;
        return false;
    }

    @Override
    public void onTouchEvent(MotionEvent event, CopyOnWriteArrayList<Missile> m_pmslist) {  // 모든 터치 이벤트 무효화.

    }

    @Override
    public int playerType() {
        return Item.UNBEATABLE;
    }  //플레이어 타입.
}
