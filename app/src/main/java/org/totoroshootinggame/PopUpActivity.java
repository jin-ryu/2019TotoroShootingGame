package org.totoroshootinggame;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import org.framework.AppManager;

public class PopUpActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
    }

    public void yesPressed(View view){
        finish();   // 액티비티(팝업) 닫기
        AppManager.getInstance().getGameView().runGameViewThread();
    }

    public void noPressed(View view){
        finish();   // 액티비티(팝업) 닫기
        System.exit(0);  // 프로그램 종료
    }

    @Override
    public void onBackPressed() {
        return; // 안드로이드 백버튼 막기
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥 레이어 클릭시 안닫히게 설정
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
