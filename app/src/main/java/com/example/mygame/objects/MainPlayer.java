package com.example.mygame.objects;

import android.graphics.Rect;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.util.UtilTimerDelay;
import com.example.mygame.classes.GameManager;
import com.example.mygame.utilites.UtilResource;
import com.example.my_framework.AnimationFW;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED= 15;
    final  int MIN_SPEED = 1;
    AnimationFW animSpriteMainPlayer;
    AnimationFW animMainPlayerBoost;

    AnimationFW animExplosionPlayer;
    CoreFW coreFW;

    boolean boosting;
    boolean levitating;

    boolean falling;

    private int shieldsPlayer;
    boolean hitEnemy;

    boolean isGameOver;

    UtilTimerDelay onShieldHit;
    UtilTimerDelay timerOnGameOver;
    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 3;
        shieldsPlayer = 3;
        boosting = false;
        hitEnemy = false;
        levitating = false;
        falling = false;
        isGameOver = false;
        radius= UtilResource.spritePlayer.get(0).getWidth()/4;
        onShieldHit = new UtilTimerDelay();
        timerOnGameOver = new UtilTimerDelay();
        this.coreFW = coreFW;
        this.maxScreenX=maxScreenX;
        this.minScreenY = minScreenY;
        this.maxScreenY=maxScreenY- UtilResource.spritePlayer.get(0).getHeight();
        animSpriteMainPlayer = new AnimationFW(1,UtilResource.spritePlayer.get(0),
                UtilResource.spritePlayer.get(1),
                UtilResource.spritePlayer.get(2),
                UtilResource.spritePlayer.get(3));
        animMainPlayerBoost = new AnimationFW(1,UtilResource.spritePlayerBoost.get(0),
                UtilResource.spritePlayerBoost.get(1),
                UtilResource.spritePlayerBoost.get(2),
                UtilResource.spritePlayerBoost.get(3));
        animExplosionPlayer = new AnimationFW(speed,
                UtilResource.spriteExplosionPlayer.get(0),
                UtilResource.spriteExplosionPlayer.get(1),
                UtilResource.spriteExplosionPlayer.get(2),
                UtilResource.spriteExplosionPlayer.get(3));
    }

    public void update() {
        if (coreFW.getTouchListenerFW().getTouchDown(0,maxScreenY,maxScreenX/2,maxScreenY)) {
            startBoosting();

        }
        if (coreFW.getTouchListenerFW().getTouchUp(0,maxScreenY,maxScreenX/2,maxScreenY)) {
            stopBoosting();
        }
        if (coreFW.getTouchListenerFW().getTouchDown(maxScreenX/2,maxScreenY,maxScreenX/2,maxScreenY/2)) {
            startLevitate();
            System.out.println("левитация!");
        }
        if (coreFW.getTouchListenerFW().getTouchUp(maxScreenX/2,maxScreenY,maxScreenX/2,maxScreenY/2)) {
            stopLevitate();
        }

        if (coreFW.getTouchListenerFW().getTouchDown(maxScreenX/2,maxScreenY/2,maxScreenX/2,maxScreenY/2)) {
            startFalling();
            System.out.println("падение!");
        }
        if (coreFW.getTouchListenerFW().getTouchUp(maxScreenX/2,maxScreenY/2,maxScreenX/2,maxScreenY/2)) {
            stopFalling();
        }

        if (boosting) {
            x+=speed;
            speed+=0.2;
        } else {
            speed-=3;
            x+=speed+GRAVITY;
        }

        if (levitating) {
            y-=6;
        }

        if (falling) {
            y+=6;
        }

        if(speed>MAX_SPEED) {
            speed=MAX_SPEED;
        }
        if (speed<MIN_SPEED) {
            speed = MIN_SPEED;
        }




        if (y<minScreenY) {
            y=minScreenY;
        }
        if (y>maxScreenY) {
            y=maxScreenY;
        }
        if (x<minScreenX) {
            x=minScreenX;
        }
        if (x>maxScreenX) {
            x=maxScreenX;
        }

        if (boosting||levitating) {
            animMainPlayerBoost.runAnimation();
        } else animSpriteMainPlayer.runAnimation();

         hitBox = new Rect(x,y
                 ,UtilResource.spritePlayer.get(0).getWidth()
                 ,UtilResource.spritePlayer.get(0).getHeight());

         if (isGameOver) {
             animExplosionPlayer.runAnimation();
         }
    }

    private void stopFalling() {
        falling = false;
    }

    private void startFalling() {
        falling = true;
    }

    private void stopLevitate() {
        levitating =false;
    }

    private void startLevitate() {
        levitating = true;
    }


    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (!isGameOver) {
            if (!hitEnemy) {
                if (boosting||levitating) {
                    animMainPlayerBoost.drawingAnimation(graphicsFW,x,y);
                } else animSpriteMainPlayer.drawingAnimation(graphicsFW,x,y);
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy,x,y);
                if (onShieldHit.timerDelay(0.2)) {
                    hitEnemy = false;
                } else hitEnemy=true;
            }
        } else {
            animExplosionPlayer.drawingAnimation(graphicsFW,x,y);
            if (timerOnGameOver.timerDelay(0.5)) {
                GameManager.gameOver=true;
            }
        }


    }

    public double getSpeedPlayer() {
        return speed;
    }

    public int getShieldsPlayer() {

        return shieldsPlayer;
    }

    public void hitEnemy() {
        shieldsPlayer--;
        if (shieldsPlayer<0) {
            isGameOver=true;
            timerOnGameOver.startTimer();
        }
        hitEnemy = true;
        onShieldHit.startTimer();
    }
}
