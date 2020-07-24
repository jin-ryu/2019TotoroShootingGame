package org.totoroshootinggame.Player;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import org.framework.AppManager;
import org.totoroshootinggame.GameObject.Item;
import org.totoroshootinggame.GameObject.Missile;
import org.totoroshootinggame.R;

import java.util.ArrayList;

public class UnbeatablePlayer extends Player{

    private Bitmap unbeatable = AppManager.getInstance().getBitmap(R.drawable.totoro_unbeatable);

    protected long ChangeTime = System.currentTimeMillis( );

    public UnbeatablePlayer()
    {
        m_bitmap = Bitmap.createScaledBitmap(unbeatable, AppManager.getInstance().getDisplayWidth(), 800, false);
        setPosition(AppManager.getInstance().getDisplayWidth() / 2 - this.getBitmapWidth() / 2,
                AppManager.getInstance().getDisplayHeight() - this.getBitmapHeight());
    }

    @Override
    public boolean destroyPlayer() {
        return false;
    }

    @Override
    public boolean isChangeType() {
        if(System.currentTimeMillis( ) > ChangeTime + 3000) return true;
        return false;
    }

    @Override
    public void onTouchEvent(MotionEvent event, ArrayList<Missile> m_pmslist) {

    }

    @Override
    public int playerType() {
        return Item.UNBEATABLE;
    }
}
