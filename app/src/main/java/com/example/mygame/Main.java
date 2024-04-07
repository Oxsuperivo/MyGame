package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.my_framework.CoreFW;
import com.example.my_framework.LoopFW;
import com.example.my_framework.SceneFW;
import com.example.mygame.classes.LoaderAssets;
import com.example.mygame.scenes.MainMenuScene;
import com.example.mygame.utilites.SettingsGame;

public class Main extends CoreFW {

    @Override
    public SceneFW getStartScene () {
        LoaderAssets loaderAssets = new LoaderAssets(this,this.getGraphicsFW());

        return new MainMenuScene(this);
    }
}