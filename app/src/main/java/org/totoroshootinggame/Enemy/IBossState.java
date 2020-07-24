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
    public boolean isChangeTime()  // 패턴 바꿀 타이밍 체크.
    {
        if(attackTime == 4) return true;
        return  false;
    }

    @Override
    public void Attack() {  // Enemy1  생성.

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
    public IBossState GetAnotherState()  //다음 패턴 반환
    {
        if(AppManager.getInstance().bossState3 == null)
            AppManager.getInstance().bossState3 = new BossPattern3(boss);
        AppManager.getInstance().bossState3.Reset();
        return AppManager.getInstance().bossState3;
    }
}

class BossPattern2 implements IBossState  //보스 패턴2
{
    Boss boss;
    long LastRegenEnemy;
    int attackTime;
    int d_x = 0;
    int d_y = 20;

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

        if(boss.getY() == AppManager.getInstance().topBar) {  // 보스 위치가 최 상단일 때

            ++attackTime;  // 카운트
            d_y = 20;    // 스피드 체크

        }

        boss.movePosition(0, d_y);
        if(boss.getY() > 3*AppManager.getInstance().getDisplayHeight()/4) d_y *= -1;  // 돌아오기
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
    public void Reset() {  // 보스 양쪽으로 움직이기.
        LastRegenEnemy = System.currentTimeMillis( );
        attackTime = 0;
        boss.SetSpeed(4.0f);
    }

    @Override
    public boolean isChangeTime() {  // 보스패턴 바꿀 타이밍.

        if(System.currentTimeMillis( ) > LastRegenEnemy + 3000) return true;
        return false;
    }

    @Override
    public void Attack() {

        boss.movePosition(d_x, 0);  // 보스 이동
        if(boss.getX() <=0 || boss.getX() + boss.getBitmapWidth() > AppManager.getInstance().getDisplayWidth() ) d_x *= -1;  // 이동 방향 반대로

    }

    @Override
    public IBossState GetAnotherState() {  //다음 패턴
        if(AppManager.getInstance().bossState2 == null)
            AppManager.getInstance().bossState2 = new BossPattern2(boss);
        AppManager.getInstance().bossState2.Reset();
        return AppManager.getInstance().bossState2;
    }
}
