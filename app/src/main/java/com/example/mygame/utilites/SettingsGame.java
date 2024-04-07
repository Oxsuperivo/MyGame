package com.example.mygame.utilites;

import android.content.SharedPreferences;

import com.example.my_framework.CoreFW;
import com.example.mygame.classes.GameManager;

public class SettingsGame {
    GameManager gameManager;
    CoreFW coreFW;
    public static int[] distance = {9999,5555,4444,3333,1111};
    public static boolean gameSaveFlag = false;


    public static void saveSettings(CoreFW coreFW) {
        SharedPreferences.Editor editor = coreFW.getSharedPreferences().edit();
        editor.clear();
        for (int i = 0; i < 5; i++) {
            editor.putInt("passedDistance"+i,distance[i]);
            editor.apply();
        }
    }

    public static void loadSettings(CoreFW coreFW) {
        for (int i = 0; i < 5; i++) {
            distance[i] = coreFW.getSharedPreferences().getInt("passedDistance"+i,distance[i]);
        }
    }

    public static void addDistance(int values) {
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < values) {
                for (int j = distance.length - 1; j > i; j--) {
                    distance[j] = distance[j - 1];
                }
                distance[i] = values;
                gameSaveFlag = true;
                break;
            }
        }

    }
    public void check() {
        if(!SettingsGame.gameSaveFlag) {

            SettingsGame.addDistance(gameManager.getPassedDistance());

            SettingsGame.saveSettings(coreFW);
        }


    }
}
