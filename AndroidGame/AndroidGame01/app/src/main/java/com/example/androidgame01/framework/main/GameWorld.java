package com.example.androidgame01.framework.main;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.androidgame01.framework.iface.Touchable;
import com.example.androidgame01.framework.iface.Recyclable;
import java.util.ArrayList;


public class GameWorld {
    private static final String TAG = GameWorld.class.getSimpleName();
    protected RecyclePool recyclePool;
    protected ArrayList<ArrayList<GameObject>> layers;
    protected ArrayList<GameObject> trash = new ArrayList<>();
    protected Touchable capturingObject;

    public GameWorld(int layerCount) {
        layers = new ArrayList<>(layerCount);
        for (int i = 0; i < layerCount; i++) {
            Log.d(TAG, "Adding layer " + i);
            layers.add(new ArrayList<GameObject>());
        }
    }

    public void draw(Canvas canvas) {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public void update() {
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                o.update();
            }
        }
        if (trash.size() > 0) {
            clearTrash();
        }
    }

    public void add(final int layerIndex, final GameObject obj) {
        UiBridge.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layerIndex);
                objects.add(obj);
            }
        });
    }
    private void clearTrash() {
        UiBridge.post(new Runnable() {
            @Override
            public void run() {
                for (int ti = trash.size() - 1; ti >= 0; ti--) {
                    GameObject o = trash.get(ti);
                    for (ArrayList<GameObject> objects: layers) {
                        int i = objects.indexOf(o);
                        if (i >= 0) {
                            objects.remove((i));
                            break;
                        }
                    }
                    trash.remove(ti);
                    if (o instanceof Recyclable) {
                        ((Recyclable) o).recycle();
                        recyclePool.add(o);
                    }
                }
            }
        });
    }

    public RecyclePool getRecyclePool() {
        return recyclePool;
    }

    public void captureTouch(Touchable obj) {
        Log.d(TAG, "Capture: " + obj);
        capturingObject = obj;
    }
    public void releaseTouch() {
        Log.d(TAG, "Release: " + capturingObject);
        capturingObject = null;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if (capturingObject != null) {
            return capturingObject.onTouchEvent(event);
        }
        for (ArrayList<GameObject> objects: layers) {
            for (GameObject o : objects) {
                if (o instanceof Touchable) {
                    boolean ret = ((Touchable) o).onTouchEvent(event);
                    if (ret) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}