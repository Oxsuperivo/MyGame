package com.example.mygame.classes;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.mygame.utilites.UtilResource;

import java.util.ArrayList;

public class LoaderAssets {
    public LoaderAssets(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpritePlayer(graphicsFW);
        loadAsteroidObject(graphicsFW);
        loadOther(graphicsFW);
    }

    private void loadOther(GraphicsFW graphicsFW) {
        UtilResource.shieldHitEnemy = graphicsFW.newSprite(UtilResource.textureAtlas,0,0,125,125);
    }

    private void loadAsteroidObject(GraphicsFW graphicsFW) {
        UtilResource.asteroidObject = new ArrayList<>();

        UtilResource.asteroidObject.add(graphicsFW.newSprite(UtilResource.textureAtlas,0,250,125,125));
        UtilResource.asteroidObject.add(graphicsFW.newSprite(UtilResource.textureAtlas,125,250,125,125));
        UtilResource.asteroidObject.add(graphicsFW.newSprite(UtilResource.textureAtlas,250,250,125,125));
        UtilResource.asteroidObject.add(graphicsFW.newSprite(UtilResource.textureAtlas,375,250,125,125));
    }

    private void loadSpritePlayer(GraphicsFW graphicsFW) {
        UtilResource.spritePlayer = new ArrayList<>();
        UtilResource.spritePlayerBoost = new ArrayList<>();
        UtilResource.spriteExplosionPlayer = new ArrayList<>();

        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,500,250,125,125));
        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,625,250,125,125));
        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,750,250,125,125));
        UtilResource.spriteExplosionPlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,875,250,125,125));

        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas,0,0,125,125));
        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas,125,0,125,125));
        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas,250,0,125,125));
        UtilResource.spritePlayerBoost.add(graphicsFW.newSprite(UtilResource.textureAtlas,375,0,125,125));

        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,0,125,125,125));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,125,125,125,125));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,250,125,125,125));
        UtilResource.spritePlayer.add(graphicsFW.newSprite(UtilResource.textureAtlas,375,125,125,125));
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResource.textureAtlas = graphicsFW.newTexture("texture_atlas5.png");
    }
}
