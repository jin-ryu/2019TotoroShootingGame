package org.totoroshootinggame.Enemy;

import org.framework.AppManager;

import java.util.Random;

public interface IBossState {

    void Reset();
    boolean isChangeTime();
    void Attack();
    IBossState GetAnotherState();

}

class BossPattern1 implements IBossState {

    protected Boss boss;
    long LastRegenEnemy;
    int attackTime;
    Random randEnem = new Random( );

    public BossPattern1(Boss _boss)
    {
        boss = _boss;
    }

    @Override
    public void Reset() {
        LastRegenEnemy = System.currentTimeMillis( );
        attackTime = 0;
        boss.SetSpeed(2.0f);
    }

    @Override
    public boolean isChangeTime()
    {
        if(attackTime == 4) return true;
        return  false;
    }

    @Override
    public void Attack() {

        if (System.currentTimeMillis( ) - LastRegenEnemy >= 3000) {

            if(++attackTime == 4) return;

            LastRegenEnemy = System.currentTimeMillis( );

            Enemy enem = new Enemy_1();
            enem .setPosition(0, boss.getBitmapHeight()+10 + AppManager.getInstance().topBar);
            enem .movetype = randEnem .nextInt(3);
            AppManager.getInstance().m_gameState.m_enemlist .add( enem );

            enem = new Enemy_1();
            enem .setPosition(AppManager.getInstance().getDisplayWidth() - enem.getBitmapWidth(), boss.getBitmapHeight()+10 + AppManager.getInstance().topBar);
            enem .movetype = randEnem .nextInt(3);
            AppManager.getInstance().m_gameState.m_enemlist .add( enem );

        }
    }

    @Override
    public IBossState GetAnotherState()
    {
        if(AppManager.getInstance().bossState3 == null)
            AppManager.getInstance().bossState3 = new BossPattern3(boss);
        AppManager.getInstance().bossState3.Reset();
        return AppManager.getInstance().bossState3;
    }
}

class BossPattern2 implements IBossState
{
    Boss boss;
    long LastRegenEnemy;
    int attackTime;
    int d_x = 0;
    int d_y = 16;

    public BossPattern2(Boss _boss)
    {
        boss = _boss;
        Reset();
    }

    @Override
    public void Reset() {
        LastRegenEnemy = System.currentTimeMillis( );
        attackTime = 0;
        boss.SetSpeed(16.0f);
    }

    @Override
    public boolean isChangeTime()
    {
        if(attackTime == 2) return true;
        return false;
    }

    @Override
    public void Attack() {

        if(boss.getY() == AppManager.getInstance().topBar) {

            ++attackTime;
            d_y = 16;

        }

        boss.movePosition(0, d_y);
        if(boss.getY() > 3*AppManager.getInstance().getDisplayHeight()/4) d_y *= -1;
    }

    @Override
    public IBossState GetAnotherState()
    {
        if(AppManager.getInstance().bossState1 == null)
            AppManager.getInstance().bossState1 = new BossPattern1(boss);
        AppManager.getInstance().bossState1.Reset();
        return AppManager.getInstance().bossState1;
    }

}

class BossPattern3 implements IBossState{

    Boss boss;
    long LastRegenEnemy;
    int attackTime;
    int d_x = 4;
    int d_y = 0;

    public BossPattern3(Boss _boss)
    {
        boss = _boss;
    }

    @Override
    public void Reset() {
        LastRegenEnemy = System.currentTimeMillis( );
        attackTime = 0;
        boss.SetSpeed(4.0f);
    }

    @Override
    public boolean isChangeTime() {
        if(System.currentTimeMillis( ) > LastRegenEnemy + 3000) return true;
        return false;
    }

    @Override
    public void Attack() {

        boss.movePosition(d_x, 0);
        if(boss.getX() <=0 || boss.getX() + boss.getBitmapWidth() > AppManager.getInstance().getDisplayWidth() ) d_x *= -1;

    }

    @Override
    public IBossState GetAnotherState() {
        if(AppManager.getInstance().bossState2 == null)
            AppManager.getInstance().bossState2 = new BossPattern2(boss);
        AppManager.getInstance().bossState2.Reset();
        return AppManager.getInstance().bossState2;
    }
}
