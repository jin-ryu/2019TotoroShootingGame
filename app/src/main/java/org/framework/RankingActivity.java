package org.framework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;


import org.DB.RankingDAO;
import org.totoroshootinggame.R;

import java.util.List;

public class RankingActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 사운드 다시 시작
        SoundManager.getInstance().start();

        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ranking);

        List<String> ranking = RankingDAO.getInstance(this).RankData();  // 랭킹 값 가져오기.
        TextView textView = null;

        for(int i = 0; i<ranking.size(); i++)
        {
            if(i == 0)  textView = findViewById(R.id.Rank1);
            else if(i == 1) textView = findViewById(R.id.Rank2);
            else if(i == 2) textView = findViewById(R.id.Rank3);
            else return;

            textView.setText((i +1) + "위 : " + ranking.get(i));
        }

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
