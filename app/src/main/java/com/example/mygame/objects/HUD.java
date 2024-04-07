package com.example.mygame.objects;

import android.graphics.Color;

import com.example.my_framework.CoreFW;
import com.example.my_framework.GraphicsFW;
import com.example.mygame.R;

public class HUD {
    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    CoreFW coreFW;
    private final int HEIGHT_HUD = 50;

    public HUD(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public void update(int passedDistance,int currentSpeedPlayer,int currentShieldsPlayer) {
        this.currentShieldsPlayer = currentShieldsPlayer;
        this.currentSpeedPlayer = currentSpeedPlayer;
        this.passedDistance = passedDistance;
    }

    public void drawing(GraphicsFW graphicsFW) {
        graphicsFW.drawLine(0,HEIGHT_HUD,graphicsFW.getWidthFrameBuffer(),HEIGHT_HUD, Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_passedDistance)+":"+passedDistance,10,30,Color.BLUE,25,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentSpeedPlayer)+":"+currentSpeedPlayer,400,30,Color.BLUE,25,null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentShieldPlayer)+":"+currentShieldsPlayer,900,30,Color.BLUE,25,null);

    }

    public int getHEIGHT_HUD() {
        return HEIGHT_HUD;
    }
}
