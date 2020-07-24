package org.framework;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import org.totoroshootinggame.State.EndingState;
import org.totoroshootinggame.State.BossState;
import org.totoroshootinggame.State.GameState;
import org.totoroshootinggame.State.IntermediateSceneState;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameViewThread thread;
    private IState state;
    private long gameStartTime;
    private boolean isBossTurn;
    public  boolean endTurn;

    public GameView(Context context){
        super(context);

        // 뷰, 컨텍스트 AppManager에 전달
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());

        // State 지정
        state = new GameState();
        changeGameState(state);

        // 콜백함수 등록
        getHolder().addCallback(this);

        gameStartTime = System.currentTimeMillis( );
        isBossTurn = false;
        endTurn = false;

    }

    public void runGameViewThread(){
        thread = new GameViewThread(getHolder(), this);
        // 스레드를 실행 상태로 만듦
        thread.setRun(true);
        // 스레드 실행
        thread.start();
    }

    public void stopGameViewThread(){
        boolean retry = true;
        thread.setRun(false);
        while (retry) {
            try {
                thread.join();  // 스레드를 중지
                retry = false;
            } catch (InterruptedException e) {
                // 스레드가 종료 되도록 계속 시도
            }
        }
    }

    public void changeGameState(IState _state){
        if(state != null)
            state.Destroy();  // 기존에 상태가 존재한다면 상태 소멸

        // 새로운 상태 적용
        _state.Init();
        state = _state;
    }

    public void Update(){


        if(endTurn)
        {
            changeGameState(new EndingState());
            endTurn = false;
        }


        else if(System.currentTimeMillis( ) > gameStartTime + 30000) {

            if (System.currentTimeMillis() < gameStartTime + 35000) {
                if (!isBossTurn) {

                    isBossTurn = true;
                    changeGameState(new IntermediateSceneState());

                }
            } else if (isBossTurn) {
                changeGameState(new BossState());
                isBossTurn = false;
            }

        }


        state.Update();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        runGameViewThread();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return state.onTouchEvent(event);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        stopGameViewThread();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        state.Render(canvas);
    }

    public void finishActivity()
    {
        GameActivity gameActivity = (GameActivity)this.getContext();
        gameActivity.finish();
    }
}

