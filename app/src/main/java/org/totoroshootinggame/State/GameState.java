package org.totoroshootinggame.State;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import org.framework.AppManager;
import org.framework.GraphicObject;
import org.framework.IState;
import org.totoroshootinggame.BackGround;
import org.totoroshootinggame.CollisionManager;
import org.totoroshootinggame.Enemy.Boss;
import org.totoroshootinggame.Enemy.Enemy;
import org.totoroshootinggame.Enemy.Enemy_1;
import org.totoroshootinggame.Enemy.Enemy_2;
import org.totoroshootinggame.Enemy.Enemy_3;
import org.totoroshootinggame.Item;
import org.totoroshootinggame.Missile;
import org.totoroshootinggame.Player.Player;
import org.totoroshootinggame.R;

import java.util.ArrayList;
import java.util.Random;

public class GameState implements IState {
    private BackGround backGround;
    private Player player;
    private Boss boss;

    public ArrayList<Missile> m_pmslist = new ArrayList<Missile>();  // 미사일 리스트
    public ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>( );   // 적 리스트
    public ArrayList<Item> m_itemlist = new ArrayList<Item>();  // 아이템 리스트

    private long LastItemTime = System.currentTimeMillis( );

    private Random randEnem = new Random( );
    private long LastRegenEnemy = System.currentTimeMillis( );

    public GameState() {
        AppManager.getInstance( ). m_gameState = this;
    }

    public void MakeEnemy( ) {

        if (System.currentTimeMillis( ) - LastRegenEnemy >= 1000) {
            LastRegenEnemy = System.currentTimeMillis( );
            int enemtype = randEnem .nextInt(3);
            Enemy enem = null;
            if ( enemtype == 0) enem = new Enemy_1();
            else if ( enemtype == 1) enem = new Enemy_2();
            else if ( enemtype == 2) enem = new Enemy_3();

            enem .setPosition( randEnem .nextInt(280), -60);
            enem .movetype = randEnem .nextInt(3);
            m_enemlist .add( enem );
        }

    }

    public void MakeItem()
    {
        if (System.currentTimeMillis( ) - LastItemTime >= 3000) {
            LastItemTime = System.currentTimeMillis( );
            int itemtype = randEnem .nextInt(4);
            Item item = null;

            if ( itemtype == 0) item = new Item(AppManager.getInstance( ).getBitmap(R.drawable. item1),0);
            else if ( itemtype == 1) item = new Item(AppManager.getInstance( ).getBitmap(R.drawable. item2),1);
            else if ( itemtype == 2) item = new Item(AppManager.getInstance( ).getBitmap(R.drawable. item3),2);
            else if ( itemtype == 3) item = new Item(AppManager.getInstance( ).getBitmap(R.drawable. item1),3);

            item.setPosition( randEnem.nextInt(AppManager.getInstance().getDisplayWidth() - 200) + 10,boss.getBitmapHeight());
            m_itemlist.add(item);
        }
    }

    public void CheckCollision( ) {
        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            for (int j = m_enemlist.size() - 1; j >= 0; j--) {
                if (CollisionManager.CheckBoxToBox(
                        m_pmslist.get(i).m_BoundBox,
                        m_enemlist.get(j).m_BoundBox)) {

                    if(m_enemlist.get(j).getEnemyType() == 3) {
                        int x = m_enemlist.get(j).getX();
                        int y = m_enemlist.get(j).getY();
                        Enemy enem1 = new Enemy_1();
                        enem1.movetype = Enemy.MOVE_PATTERN_2;
                        enem1.setPosition(x,y);
                        Enemy enem2 = new Enemy_1();
                        enem2.movetype = Enemy.MOVE_PATTERN_3;
                        enem2.setPosition(x,y);
                        m_enemlist.add(enem1);
                        m_enemlist.add(enem2);
                    }

                    m_pmslist.remove(i);
                    m_enemlist.remove(j);
                    return; // 일단 루프에서 빠져나옴
                }
            }
        }

        for (int i = m_enemlist.size() - 1; i >= 0; i--) {
            if (CollisionManager.CheckBoxToBox(player.m_BoundBox, m_enemlist.get(i).m_BoundBox)) {
                m_enemlist .remove( i );
                //player .destroyPlayer( );
                //if ( player.getLife( ) <= 0 ) System.exit( 0 );
            }
        }

    }

    @Override
    public void Init() {
        backGround = new BackGround(0);
        player = new Player(0);
        boss = new Boss(AppManager.getInstance( ).getBitmap(R.drawable.boss1));
    }

    @Override
    public void Destroy() {

    }

    @Override
    public void Update() {
        long gameTime = System.currentTimeMillis();
        player.Update(gameTime);
        backGround.Update(gameTime);
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

        boss.update();
        MakeEnemy( );
        MakeItem();
        CheckCollision( );
    }

    @Override
    public void Render(Canvas canvas) {
        backGround.Draw(canvas);
        for (Missile pms : m_pmslist) pms.Draw(canvas);
        for (Enemy enem : m_enemlist) enem.Draw(canvas);
        for (Item item : m_itemlist) item.Draw(canvas);
        player.Draw(canvas);
        boss.Draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = player.getX();
        int y = player.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            Missile missile = new Missile();
            missile.setPosition(x + missile.getBitmapWidth()/2, y);
            m_pmslist.add(missile);
        }
        return false;
    }
}

