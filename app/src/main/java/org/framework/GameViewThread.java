package org.framework;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread{
    // 접근을 위한 맴버 변수
    private SurfaceHolder surfaceHolder;
    private GameView gameView;

    // 스래드 실행 상태 맴버 변수
    private boolean run = false;

    public GameViewThread(SurfaceHolder _surfaceHolder, GameView _gameView){
        surfaceHolder = _surfaceHolder;
        gameView = _gameView;
    }

    public void setRun(boolean _run){
        run = _run;
    }

    @Override
    public void run() {
        Canvas _canvas;
        while (run){
            _canvas = null;
            try {   // SurfaceHolder를 통해 Surface에 접근해서 가져옴
                gameView.Update();
                _canvas = surfaceHolder.lockCanvas(null); // canvas object 얻음
                synchronized (surfaceHolder){
                    // holder가 잡고 있는 surface에 대해서 동기식으로 업데이트가 진행되도록 해줌
                    gameView.onDraw(_canvas); // 그림을 그림
                }

            }finally {
                if(_canvas != null)
                    // surface를 화면에 표시함 (스크린에 반영)
                    surfaceHolder.unlockCanvasAndPost(_canvas);

            }
        }
    }

}
