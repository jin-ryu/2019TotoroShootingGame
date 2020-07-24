package org.totoroshootinggame.State;

import android.graphics.Canvas;

import org.DB.RankingDAO;
import org.framework.AppManager;
import org.framework.GraphicObject;
import org.framework.SoundManager;
import org.totoroshootinggame.GameObject.BackGround;
import org.totoroshootinggame.GameObject.CloudLayer;
import org.totoroshootinggame.CollisionManager;
import org.totoroshootinggame.Enemy.Boss;
import org.totoroshootinggame.Enemy.Enemy;
import org.totoroshootinggame.GameObject.Item;
import org.totoroshootinggame.GameObject.Missile;
import org.totoroshootinggame.R;

public class BossState extends GameState {

    Boss boss;

    @Override
    public void MakeEnemy() {

    }

    @Override
    public void Init() {
        SoundManager.getInstance().playBackground(AppManager.getInstance().getGameView().getContext(),R.raw.background3);
        backGround = new BackGround(1);
        boss = new Boss(AppManager.getInstance( ).getBitmap(R.drawable.boss1));
        player = AppManager.getInstance().player;
        AppManager.getInstance().player_state = player;
        cloudLayer = new CloudLayer();
        for(int heart = 0 ; heart < player.getLife();heart++)
            insertHeart(heart);
    }

    @Override
    public void Update() {
        super.Update();
        boss.update();
    }

    @Override
    public void Render(Canvas canvas) {
        // 구름 디버프 시간 확인
        if(System.currentTimeMillis( ) > LastCloudTime + 5000){
            isCloudFront = false;
            LastCloudTime = 0;
        }

        if(isCloudFront){
            // 구름 앞으로 나옴
            backGround.Draw(canvas);
            for (Missile pms : m_pmslist) pms.Draw(canvas);
            for (Enemy enem : m_enemlist) enem.Draw(canvas);
            for (Item item : m_itemlist) item.Draw(canvas);
            for (GraphicObject heart:heartlist) heart.Draw(canvas);
            player.Draw(canvas);
            boss.Draw(canvas);
            cloudLayer.Draw(canvas);

        }
        else{
            // 구름 백그라운드로 이동
            backGround.Draw(canvas);
            cloudLayer.Draw(canvas);
            for (Missile pms : m_pmslist) pms.Draw(canvas);
            for (Enemy enem : m_enemlist) enem.Draw(canvas);
            for (Item item : m_itemlist) item.Draw(canvas);
            for (GraphicObject heart:heartlist) heart.Draw(canvas);
            player.Draw(canvas);
            boss.Draw(canvas);
        }
    }

    @Override
    public void MakeItem() {  // 아이템 생성
        if (System.currentTimeMillis( ) - LastItemTime >= 3000) {
            LastItemTime = System.currentTimeMillis( );
            Item item = getItem();
            item.setPosition( randEnem.nextInt(AppManager.getInstance().getDisplayWidth() - 200) + 10,boss.getBitmapHeight() + 100 + AppManager.getInstance().topBar);
            m_itemlist.add(item);
        }
    }

    @Override
    public void CheckCollision() {
        super.CheckCollision();
        if (CollisionManager.CheckBoxToBox(player.m_BoundBox, boss.m_BoundBox)) {  //보스 충돌
            if(player .destroyPlayer( )) heartlist.remove(heartlist.size() - 1);   //***
            if(player.playerType() == Item.STATE_NORMAL) vibrator.vibrate(100);
            if ( player.getLife( ) <= 0 )
            {
                RankingDAO.getInstance(AppManager.getInstance().getGameView().getContext()).insert(player.getPoint());
                AppManager.getInstance().getGameView().endTurn = true;
            }
        }

        for (int i = m_pmslist.size() - 1; i >= 0; i--)  //미사일 <-> 보스 충돌
        {
            if (CollisionManager.CheckBoxToBox(m_pmslist.get(i).m_BoundBox , boss.m_BoundBox))
            {
                m_pmslist.remove(i);
                boss.destroyBoss();
                player.addPoint();
                if(boss.getBossHp() == 0) {
                    RankingDAO.getInstance(AppManager.getInstance().getGameView().getContext()).insert(player.getPoint());  // 여기 추가해줌
                    AppManager.getInstance().getGameView().endTurn = true;
                }
            }
        }

    }
}
