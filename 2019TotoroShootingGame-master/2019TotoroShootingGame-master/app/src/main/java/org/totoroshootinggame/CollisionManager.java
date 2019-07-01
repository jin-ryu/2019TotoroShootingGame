package org.totoroshootinggame;

import android.graphics.Rect;

/*충돌 처리 로직 담당*/
public class CollisionManager {

    public static boolean CheckBoxToBox(Rect _rt1, Rect _rt2){
        if(_rt1.right > _rt2.left && _rt1.left < _rt2.right &&
        _rt1.top < _rt2.bottom && _rt1.bottom > _rt2.top)
            return true;

        return false;
    }
}
