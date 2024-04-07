package com.example.mygame.classes;

import com.example.my_framework.CollisionDetect;
import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.mygame.generator.GeneratorBackground;
import com.example.mygame.generator.GeneratorEnemy;
import com.example.mygame.objects.HUD;
import com.example.mygame.objects.MainPlayer;

public class GameManager {
    private int maxScreenY;
    private int maxScreenX;
    private int minScreenY;
    private int minScreenX;

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    public static boolean gameOver;

    MainPlayer mainPlayer;

    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    HUD hud;

    public int getPassedDistance() {
        return passedDistance;
    }

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new HUD(coreFW);
        this.maxScreenX=sceneWidth;
        this.maxScreenY=sceneHeight;
        minScreenY=hud.getHEIGHT_HUD();
        minScreenX=0;
        mainPlayer = new MainPlayer(coreFW, maxScreenX,maxScreenY,minScreenY);
        generatorBackground = new GeneratorBackground(sceneWidth,sceneHeight,minScreenY);
        gameOver = false;
        generatorEnemy = new GeneratorEnemy(sceneWidth,sceneHeight,minScreenY);
    }
    public void update() {
        mainPlayer.update();
        generatorBackground.update(mainPlayer.getSpeedPlayer());
        generatorEnemy.update(mainPlayer.getSpeedPlayer());
        passedDistance+=mainPlayer.getSpeedPlayer();
        currentSpeedPlayer= (int) mainPlayer.getSpeedPlayer();
        currentShieldsPlayer = mainPlayer.getShieldsPlayer();

        hud.update(passedDistance,currentSpeedPlayer,currentShieldsPlayer);
        checkHit();

    }

    private void checkHit() {
        for (int i = 0; i < generatorEnemy.enemyArrayList.size(); i++) {
            if (CollisionDetect.collisionDetect(mainPlayer,generatorEnemy.enemyArrayList.get(i))) {
              mainPlayer.hitEnemy();
              generatorEnemy.hitPlayer(generatorEnemy.enemyArrayList.get(i));
            }
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawing(graphicsFW);
        generatorEnemy.drawing(graphicsFW);
        hud.drawing(graphicsFW);

    }
}
