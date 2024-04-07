package com.example.my_framework;

import android.view.MotionEvent;
import android.view.View;

public class TouchListenerFW implements View.OnTouchListener{

    float touchX;
    float touchY;

    boolean isTouchDown;
    boolean isTouchUp;

    float sceneWidth;
    float sceneHeight;

    public TouchListenerFW(View view, float sceneWidth, float sceneHeight) {
        view.setOnTouchListener(this);
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this) {
            isTouchDown = false;
            isTouchUp = false;
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchX= motionEvent.getX()*sceneWidth;
                    touchY= motionEvent.getY()*sceneHeight;
                    isTouchDown = true;
                    isTouchUp = false;
                    break;
                case MotionEvent.ACTION_UP:
                    touchX= motionEvent.getX()*sceneWidth;
                    touchY= motionEvent.getY()*sceneHeight;
                    isTouchDown = false;
                    isTouchUp = true;
                    break;
            }
        }
        return true;
    }
    public boolean getTouchUp(int x,int y, int touchWidth, int touchHeight) {
        // проверка совпадают ли области отжатия пользователя и передаваемая область, если да , то возвращаем true , то логично что пользователь отпустил палец
        //от области и мы передаем значение false в isTouchDown и
        // значение true , что именно эта область нажата, и в случае если isTouchUp false, значит область не отжата и пользователь продолжает держать там палец.
        if (isTouchUp) {
            if (touchX>=x&&touchX<=x+touchWidth-1&&touchY<=y&&touchY>=y-(touchHeight-1)) {
                isTouchUp = false;
                return true;
            }
        }
        return false;
    }
    public boolean getTouchDown(int x,int y, int touchWidth, int touchHeight) {
        // проверка совпадают ли области нажатия пользователя и передаваемая область, если да , то возвращаем true и отжимаем
        // кнопку, передавая значение true , что именно эта область нажата, и в случае если isTouchDown false, сохраняем область(кнопку) не нажатой.
        if (isTouchDown) {
            if (touchX>=x&&touchX<=x+touchWidth-1&&touchY<=y&&touchY>=y-(touchHeight-1)) {
                isTouchDown = false;
                return true;
            }
        }
        return false;
    }
}


























