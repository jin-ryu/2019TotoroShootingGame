package org.totoroshootinggame.State;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import org.framework.AppManager;
import org.framework.IState;
import org.framework.SoundManager;
import org.framework.SpriteAnimation;
import org.totoroshootinggame.R;

public class EndingState extends SpriteAnimation implements IState {  //엔딩

    public Bitmap scene = Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.end), AppManager.getInstance().getDisplayWidth()*4, AppManager.getInstance().getDisplayHeight(), false);

    private long executeTime;

    public EndingState() {
        super(Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.end), AppManager.getInstance().getDisplayWidth()*4, AppManager.getInstance().getDisplayHeight(), false));
        this.initSpriteData(scene.getWidth()/4,scene.getHeight(),5,4);
        this.setPosition(0, 0);
        executeTime = System.currentTimeMillis();
    }

    @Override
    public void Init() {
        SoundManager.getInstance().release();
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        super.Update(System.currentTimeMillis( ));
        if(System.currentTimeMillis() > executeTime + 3000)
            AppManager.getInstance().getGameView().finishActivity();
    }

    @Override
    public void Render(Canvas canvas) {
        this.Draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }


}
