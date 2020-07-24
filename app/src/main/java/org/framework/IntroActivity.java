package org.framework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;

import org.totoroshootinggame.R;

public class IntroActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro);

        SoundManager.getInstance().playBackground(this,R.raw.background1);
        AppManager.getInstance().introActivity = this;
    }

    public void goGame(View view) {
        SoundManager.getInstance().playSoundEffect(this,R.raw.effect1, 1);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void goRank(View view) {
        SoundManager.getInstance().playSoundEffect(this,R.raw.effect1, 1);
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }

    public void goExit(View view) {
        SoundManager.getInstance().playSoundEffect(this,R.raw.effect1, 1);
        //SoundManager.getInstance().release();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundManager.getInstance().pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SoundManager.getInstance().start();
    }

}
