package com.example.androidgame01.framework.obj.ui;

import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;
import android.view.MotionEvent;

import com.example.androidgame01.framework.iface.Touchable;
import com.example.androidgame01.framework.main.UiBridge;
import com.example.androidgame01.framework.obj.BitmapObject;

public class Button extends BitmapObject implements Touchable {
    private static final String TAG = Button.class.getSimpleName();
    private final NinePatchDrawable bgNormal;
    private final NinePatchDrawable bgPress;
    public boolean capturing = false, pressed =false;

    public Button(float x, float y, int resId, int bgNormalResId, int bgPressResId) {
        super(x, y, 0, 0, resId);

        this.bgNormal = (NinePatchDrawable) UiBridge.getResources().getDrawable(bgNormalResId);
        this.bgPress = (NinePatchDrawable) UiBridge.getResources().getDrawable(bgPressResId);
        int left = (int)this.x - this.width / 2, top = (int)this.y - this.height / 2;
        this.bgNormal.setBounds(left, top, left + this.width, top + this.height);
        this.bgPress.setBounds(left, top, left + this.width, top + this.height);
    }

    @Override
    public void draw(Canvas canvas) {
        NinePatchDrawable bg = pressed ? bgPress : bgNormal;
        bg.draw(canvas);
        super.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (this.bgNormal.getBounds().contains((int)e.getX(), (int)e.getY())) {
                    Log.d(TAG, "Down");
                    captureTouch();
                    capturing = true;
                    pressed = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                pressed = this.bgNormal.getBounds().contains((int)e.getX(), (int)e.getY());
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "Up");
                releaseTouch();
                capturing = false;
                pressed = false;
                if (this.bgNormal.getBounds().contains((int)e.getX(), (int)e.getY())) {
                    Log.d(TAG, "TouchUp Inside");
                }
                return true;
        }
        return false;
    }
}
