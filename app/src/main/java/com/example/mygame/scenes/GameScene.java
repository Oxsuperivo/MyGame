package com.example.mygame.scenes;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.mygame.R;
import com.example.mygame.classes.GameManager;
import com.example.mygame.generator.GeneratorBackground;
import com.example.mygame.utilites.SettingsGame;
import com.example.mygame.utilites.UtilResource;

public class GameScene extends SceneFW {
    enum GameState {
        READY,RUNNING,PAUSE,GAMEOVER
    }

    GameState gameState;


    GameManager gameManager;
    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;

        gameManager = new GameManager(coreFW,sceneWidth,sceneHeight);
    }

    @Override
    public void update() {
        if (gameState==GameState.READY) {
            updateStateReady();
        }
        if (gameState==GameState.RUNNING) {
            updateStateRunning();
        }
        if (gameState==GameState.PAUSE) {
            updateStatePause();
        }
        if (gameState==GameState.GAMEOVER) {
            updateStateGameOver();
        }
    }



    @Override
    public void drawing() {

        if (gameState==GameState.READY) {
            drawingStateReady();
        }
        if (gameState==GameState.RUNNING) {
            drawingStateRunning();
        }
        if (gameState==GameState.PAUSE) {
            drawingStatePause();
        }
        if (gameState==GameState.GAMEOVER) {
            drawingStateGameOver();
        }
    }

    private void updateStateGameOver() {
        if(!SettingsGame.gameSaveFlag) {
            SettingsGame.addDistance(gameManager.getPassedDistance());
            SettingsGame.saveSettings(coreFW);
        }


        if (coreFW.getTouchListenerFW().getTouchUp(500,460,100,30)) {
            coreFW.setScene(new GameScene(coreFW));
        }
        if (coreFW.getTouchListenerFW().getTouchUp(500,520,100,30)) {
            coreFW.setScene(new MainMenuScene(coreFW));
        }

    }
    private void drawingStateGameOver() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver),500,400,Color.WHITE,60,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart),500,460,Color.WHITE,30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit),500,520,Color.WHITE,30,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance)+":"+gameManager.getPassedDistance(),500,580,Color.WHITE,30,null);
    }
    private void updateStatePause() {
    }
    private void drawingStatePause() {
    }

    private void updateStateRunning() {

        gameManager.update();
        if (GameManager.gameOver) {
            gameState = GameState.GAMEOVER;
        }
    }
    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);

        gameManager.drawing(coreFW,graphicsFW);

    }

    private void updateStateReady() {
        if (coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight,sceneWidth,sceneHeight)) {
            gameState= GameState.RUNNING;
        }
    }
    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready),250,300,Color.WHITE,60,null);
    }
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
