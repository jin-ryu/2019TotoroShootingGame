package org.framework;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import org.framework.AppManager;
import org.totoroshootinggame.R;

import java.util.List;

public class PopUpActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        SoundManager.getInstance().pause();
    }

    public void yesPressed(View view){
        SoundManager.getInstance().playSoundEffect(this,R.raw.effect1, 1);
        finish();   // 액티비티(팝업) 닫기
        SoundManager.getInstance().start();
        AppManager.getInstance().getGameView().runGameViewThread();
    }

    public void noPressed(View view){
        finish();   // 액티비티(팝업) 닫기
        System.exit(0);
        SoundManager.getInstance().playSoundEffect(this,R.raw.effect1, 1);
    }

    @Override
    public void onBackPressed() {
        return; // 안드로이드 백버튼 막기
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 바깥 레이어 클릭시 안닫히게 설정
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
