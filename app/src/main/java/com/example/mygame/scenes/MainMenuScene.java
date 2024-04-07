package com.example.mygame.scenes;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.LoopFW;
import com.example.my_framework.SceneFW;
import com.example.mygame.R;
import com.example.mygame.classes.GameManager;
import com.example.mygame.generator.GeneratorBackground;
import com.example.mygame.utilites.SettingsGame;
import com.example.mygame.utilites.UtilResource;

public class MainMenuScene extends SceneFW {
    LoopFW loopFW;
    GeneratorBackground generatorBackground;
    GameManager gameManager;
    public MainMenuScene(CoreFW coreFW) {
        super(coreFW);
    }

    @Override
    public void update() {
if (coreFW.getTouchListenerFW().getTouchUp(20,420,400,80)) {
        coreFW.setScene(new GameScene(coreFW));

}
        if (coreFW.getTouchListenerFW().getTouchUp(20,540,400,80)) {
            coreFW.setScene(new TopDistance(coreFW));

        }
    }

    @Override
    public void drawing() {
    graphicsFW.clearScene(Color.BLACK);
    graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_nameGame),20,200,Color.BLUE,100,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_newGame),20,420,Color.BLUE,80,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_results),20,540,Color.BLUE,80,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_settings),20,660,Color.BLUE,80,null);
        graphicsFW.drawTexture(UtilResource.spritePlayer.get(0), 740,100);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        SettingsGame.loadSettings(coreFW);
    }

    @Override
    public void dispose() {

    }
}
