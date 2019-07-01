package org.framework;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {

    public void Init(); // 상태 생성

    public void Destroy();  // 상태 소멸

    public void Update();   // 데이터 갱신

    public void Render(Canvas canvas);  // 화면 그림

    public boolean onTouchEvent(MotionEvent event); // 터치 입력 처리
}

// Render와 Update를 따로 두는 이유
// 게임에서는 화면이 각각의 thread로 따로 동작하고, 이에 따른 Update 속도도 다름
// 이때 다른 속도로 화면이 그려지는 것을 막고 update가 끝난 후에 render가 진행되게 하기 위해 따로 구현