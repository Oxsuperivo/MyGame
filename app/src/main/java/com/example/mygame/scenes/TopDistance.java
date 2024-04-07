package com.example.mygame.scenes;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.SceneFW;
import com.example.mygame.R;
import com.example.mygame.utilites.SettingsGame;

public class TopDistance extends SceneFW {
    String[] numbers = new String[5];


    public TopDistance(CoreFW coreFW) {
        super(coreFW);
        for (int i = 0; i < 5; i++) {
            this.numbers[i]=" "+(i+1)+". " + SettingsGame.distance[i];
        }
    }

    @Override
    public void update() {
      if (coreFW.getTouchListenerFW().getTouchUp(0,sceneHeight,sceneWidth,sceneHeight)) {
          coreFW.setScene(new MainMenuScene(coreFW));
      }
    }

    @Override
    public void drawing() {

        graphicsFW.drawText(coreFW.getString(R.string.txt_top_distance),100,100, Color.BLUE,40,null);
        graphicsFW.drawText(String.valueOf(numbers[0]),100,200,Color.BLUE,35,null);
        graphicsFW.drawText(String.valueOf(numbers[1]),100,245,Color.BLUE,35,null);
        graphicsFW.drawText(String.valueOf(numbers[2]),100,280,Color.BLUE,35,null);
        graphicsFW.drawText(String.valueOf(numbers[3]),100,315,Color.BLUE,35,null);
        graphicsFW.drawText(String.valueOf(numbers[4]),100,350,Color.BLUE,35,null);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        graphicsFW.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }
}
