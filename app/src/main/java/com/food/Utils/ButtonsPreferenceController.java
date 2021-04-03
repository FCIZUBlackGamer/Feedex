package com.food.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ButtonsPreferenceController {
    private static final String DATABASE_NAME = "SmartButtons";
    public static  final String FEED1Num ="feed1Num";
    public static  final String FEED1Key ="feed1Key";
    public static  final String FEED2Num ="feed2Num";
    public static  final String FEED2Key ="feed2Key";
    public static  final String FEED3Num ="feed3Num";
    public static  final String FEED3Key ="feed3Key";
    private static ButtonsPreferenceController instance;
    private SharedPreferences preferences;


    public static ButtonsPreferenceController getInstance(Context context) {
        if (instance == null) instance = new ButtonsPreferenceController(context, DATABASE_NAME);
        return instance;
    }

    private ButtonsPreferenceController(Context context, String databaseName) {
        preferences = context.getSharedPreferences(databaseName, 0);
    }

    public void persist(String key, String val) {
        preferences.edit().putString(key, val).apply();
    }

    public String get(String key) {
        return preferences.getString(key, "");
    }

    public void clear(String key) {
        persist(key, "");
    }

    public static void alertSound(Context context){
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Context context, View view){
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    view.getApplicationWindowToken(),
                    InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
