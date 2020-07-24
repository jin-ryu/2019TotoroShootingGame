package org.totoroshootinggame.State;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.view.MotionEvent;

import org.DB.RankingDAO;
import org.framework.AppManager;
import org.framework.GraphicObject;
import org.framework.IState;
import org.totoroshootinggame.GameObject.BackGround;
import org.totoroshootinggame.GameObject.CloudLayer;
import org.totoroshootinggame.CollisionManager;
import org.totoroshootinggame.Enemy.Enemy;
import org.totoroshootinggame.Enemy.Enemy_1;
import org.totoroshootinggame.Enemy.Enemy_2;
import org.totoroshootinggame.Enemy.Enemy_3;
import org.totoroshootinggame.GameObject.Item;
import org.totoroshootinggame.GameObject.Missile;
import org.totoroshootinggame.Player.Player;
import org.totoroshootinggame.Player.ShieldPlayer;
import org.totoroshootinggame.Player.UnbeatablePlayer;
import org.totoroshootinggame.R;

import java.util.ArrayList;
import java.util.Random;

public class GameState implements IState {

    protected BackGround backGround;
    protected Player player;
    protected CloudLayer cloudLayer;

    public ArrayList<Missile> m_pmslist = new ArrayList<Missile>();  // 미사일 리스트
    public ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>( );   // 적 리스트
    public ArrayList<Item> m_itemlist = new ArrayList<Item>();  // 아이템 리스트
    protected ArrayList<GraphicObject> heartlist = new ArrayList<GraphicObject>();  //***

    protected long LastItemTime = System.currentTimeMillis( );
    protected long LastCloudTime = 0;

    protected Random randEnem = new Random( );
    protected long LastRegenEnemy = System.currentTimeMillis( );

    protected Vibrator vibrator =  (Vibrator) AppManager.getInstance().getGameView().getContext().getSystemService(Context.VIBRATOR_SERVICE);

    protected boolean isCloudFront = false;

    public GameState() {
        AppManager.getInstance( ). m_gameState = this;
    }

    public void MakeEnemy( ) {

        if (System.currentTimeMillis( ) - LastRegenEnemy >= 2000) {
            LastRegenEnemy = System.currentTimeMillis( );
            int enemtype = randEnem .nextInt(3);
            Enemy enem = null;
            if ( enemtype == 0) enem = new Enemy_1();
            else if ( enemtype == 1) enem = new Enemy_2();
            else if ( enemtype == 2) enem = new Enemy_3();

            enem .setPosition( randEnem .nextInt(280), AppManager.getInstance().topBar);
            enem .movetype = randEnem .nextInt(3);
            m_enemlist .add( enem );

        }
    }

    public Item getItem() {

        int itemtype = randEnem.nextInt(4);
        Item item = null;

        if (itemtype == 0)
            item = new Item(AppManager.getInstance().getBitmap(R.drawable.item1), 0);
        else if (itemtype == 1)
            item = new Item(AppManager.getInstance().getBitmap(R.drawable.item2), 1);
        else if (itemtype == 2)
            item = new Item(AppManager.getInstance().getBitmap(R.drawable.item3), 2);
        else if (itemtype == 3)
            item = new Item(AppManager.getInstance().getBitmap(R.drawable.item7), 3);  //***

        return item;
    }

    public void MakeItem()
    {
        if (System.currentTimeMillis( ) - LastItemTime >= 3000) {
            LastItemTime = System.currentTimeMillis( );
            Item item = getItem();
            item.setPosition( randEnem.nextInt(AppManager.getInstance().getDisplayWidth() - 200) + 10, AppManager.getInstance().topBar);
            m_itemlist.add(item);
        }
    }

    public void CheckCollision( ) {

        // 미사일과 적 충돌
        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            for (int j = m_enemlist.size() - 1; j >= 0; j--) {
                if (CollisionManager.CheckBoxToBox(
                        m_pmslist.get(i).m_BoundBox,
                        m_enemlist.get(j).m_BoundBox)) {

                    if(m_enemlist.get(j).getEnemyType() == 3)
                    {
                        int x = m_enemlist.get(j).getX();
                        int y = m_enemlist.get(j).getY();
                        Enemy enem1 = new Enemy_1();
                        enem1.movetype = Enemy.MOVE_PATTERN_2;
                        enem1.setPosition(x,y);
                        Enemy enem2 = new Enemy_1();
                        enem2.movetype = Enemy.MOVE_PATTERN_3;
                        enem2.setPosition(x,y);
                        m_enemlist.add( enem1 );
                        m_enemlist.add( enem2 );

                    }

                    m_pmslist.remove(i);
                    m_enemlist.remove(j);
                    player.addPoint();
                    return; // 일단 루프에서 빠져나옴
                }
            }
        }

        // 플레이어와 적 충돌
        for (int i = m_enemlist.size() - 1; i >= 0; i--) {
            if (CollisionManager.CheckBoxToBox(player.m_BoundBox, m_enemlist.get(i).m_BoundBox)) {
                m_enemlist .remove( i );
                if(player .destroyPlayer( )) heartlist.remove(heartlist.size() - 1);   //***
                if(player.playerType() == Item.STATE_NORMAL ) vibrator.vibrate(100);
                if ( player.getLife( ) <= 0 )
                {
                    RankingDAO.getInstance(AppManager.getInstance().getGameView().getContext()).insert(player.getPoint());
                    AppManager.getInstance().getGameView().endTurn = true;
                }
            }
        }

        // 플레이어와 아이템 충돌
        for (int i = m_itemlist.size() - 1; i >= 0; i--) {

            if (CollisionManager.CheckBoxToBox(player.m_BoundBox, m_itemlist.get(i).m_BoundBox)) {
                int itemType = m_itemlist.get(i).getItemType();
                m_itemlist.remove(i);
                if(player.playerType() == Item.UNBEATABLE) break;
                switch (itemType)
                {
                    case Item.HEART: player.addLife();
                        insertHeart(heartlist.size());  //***
                        break;

                    case Item.SHIELD:
                        int x = player.getX();
                        int y = player.getY();
                        player = new ShieldPlayer();
                        player.setPosition(x,y);
                        AppManager.getInstance().player_state = player;
                        break;

                    case Item.UNBEATABLE:
                        player = new UnbeatablePlayer();
                        AppManager.getInstance().player_state = player;
                        break;

                    case Item.CLOUD:
                        isCloudFront = true;
                        LastCloudTime = System.currentTimeMillis();
                        break;
                }
            }
        }
    }

    @Override
    public void Init() {
        backGround = new BackGround(0);
        player = new Player();
        cloudLayer = new CloudLayer();
        AppManager.getInstance().player = player;
        AppManager.getInstance().player_state = player;
        for(int heart = 0 ; heart < player.getLife();heart++) //***
            insertHeart(heart); //***

    }

    @Override
    public void Destroy() {
    }

    @Override
    public void Update() {
        long gameTime = System.currentTimeMillis();
        player.Update();
        backGround.Update(gameTime);
        cloudLayer.Update(gameTime);

        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            Missile pms = m_pmslist.get(i);
            pms.Update();
            if (pms.state == Missile.STATE_OUT)
                m_pmslist.remove(i);
        }
        for (int i = m_enemlist .size( )-1; i >= 0; i--) {
            Enemy enem = m_enemlist .get( i );
            enem.Update(gameTime);
            if (enem. state == Enemy. STATE_OUT) m_enemlist .remove( i );
        }

        for (int i = m_itemlist.size( )-1; i >= 0; i--) {
            Item item = m_itemlist .get( i );
            item.Update(gameTime);
            if (item. state == Enemy. STATE_OUT) m_itemlist .remove( i );
        }

        if(player.isChangeType()) {
            player = AppManager.getInstance().player;
            AppManager.getInstance().player_state = player;
        }
        MakeEnemy();
        MakeItem();
        CheckCollision( );
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
            cloudLayer.Draw(canvas);
            player.Draw(canvas);
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
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        player.onTouchEvent(event,m_pmslist);
        return false;
    }

    protected void insertHeart(int i)   // 이 함수 전체~
    {
        GraphicObject heart = new GraphicObject(Bitmap.createScaledBitmap(AppManager.getInstance().getBitmap(R.drawable.item1), 100,AppManager.getInstance().topBar- 10, false));
        heart.setPosition( 5 + 110 * i, 5);
        heartlist.add(heart);
    }
}

