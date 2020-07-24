package org.totoroshootinggame.State;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

import org.framework.AppManager;
import org.framework.IState;
import org.framework.SpriteAnimation;
import org.totoroshootinggame.R;

public class IntermediateSceneState extends SpriteAnimation implements IState {

    public Bitmap scene = Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.boss), AppManager.getInstance().getDisplayWidth()*4, AppManager.getInstance().getDisplayHeight(), false);

    public IntermediateSceneState() {
        super(Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.boss), AppManager.getInstance().getDisplayWidth()*4, AppManager.getInstance().getDisplayHeight(), false));
        this.initSpriteData(scene.getWidth()/4,scene.getHeight(),5,4);
        this.setPosition(0, 0);
    }

    @Override
    public void Init() {

    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        super.Update(System.currentTimeMillis( ));
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
