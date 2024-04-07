package com.example.mygame.objects;

import android.graphics.Rect;

import com.example.my_framework.AnimationFW;
import com.example.my_framework.GraphicsFW;
import com.example.my_framework.ObjectFW;
import com.example.my_framework.util.UtilRandomFW;
import com.example.mygame.classes.GameManager;
import com.example.mygame.utilites.UtilResource;

public class Enemy extends ObjectFW {
    AnimationFW animEnemy;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        this.maxScreenX=maxScreenX;
        this.maxScreenY=maxScreenY- UtilResource.asteroidObject.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX=0;
        radius = UtilResource.asteroidObject.get(0).getWidth()/4;

        x= maxScreenX;
        y= UtilRandomFW.getGap(minScreenY,maxScreenY);

        switch (enemyType) {
            case 1:
                speed=UtilRandomFW.getGap(1,7);
                animEnemy = new AnimationFW(3,
                        UtilResource.asteroidObject.get(0),
                        UtilResource.asteroidObject.get(1),
                        UtilResource.asteroidObject.get(2),
                        UtilResource.asteroidObject.get(3)
                        );
                break;
            case 2:
                speed= UtilRandomFW.getGap(4,9);
                break;
        }
    }
    public void update(double speedPlayer) {
        x-=speed;
        x-=speedPlayer;
        if (x<minScreenX) {
            x=maxScreenX;
            y = UtilRandomFW.getGap(minScreenY,maxScreenY);
        }
        animEnemy.runAnimation();

        hitBox = new Rect(x,y
                ,UtilResource.asteroidObject.get(0).getWidth()
                ,UtilResource.asteroidObject.get(0).getHeight());

    }
    public void  drawing(GraphicsFW graphicsFW) {
        animEnemy.drawingAnimation(graphicsFW,x,y);
    }
}
