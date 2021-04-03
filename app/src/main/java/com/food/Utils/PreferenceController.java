package com.food.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class PreferenceController {
    private static final String DATABASE_NAME = "ZagTechIotLLC";
    public static final String LANGUAGE = "lang";
    public static final String PREF_EMAIL = "email";
    public static final String PREF_USER_NAME = "username";
    public static final String PREF_DEVICE_TOKEN = "token";
    public static final String PREF_USER_PASSWORD = "password";
    public static final String PREF_USER_ID = "userId";
    public static final String PREF_EDIT_MODE = "editMode";
    public static final String PREF_USER_DEVICES_LIST = "userList";
    public static final String PREF_USER_NUM_PLACES = "numPlaces";
    public static final String PREF_USER_NUM_ROOMS = "numRooms";
    public static final String AirConditioner = "15";
    public static final String BigFan = "1";
    public static final String DeskLamp = "2";
    public static final String Fan = "3";
    public static final String Fridge = "4";
    public static final String Heater = "5";
    public static final String HeatSensor = "6";
    public static final String Lamp = "7";
    public static final String LightSensor = "8";
    public static final String Microwave = "9";
    public static final String Shutter = "10";
    public static final String StandFan = "11";
    public static final String TV = "12";
    public static final String WaterHeater = "13";
    public static final String Sound = "14";
    public static final String FEED1 = "feed1";
    public static final String FEED2 = "feed2";
    public static final String FEED3 = "feed3";
    public static final String FEED4 = "feed4";
    public static final String FEED1Num = "feed1Num";
    public static final String FEED1Key = "feed1Key";
    public static final String FEED2Num = "feed2Num";
    public static final String FEED2Key = "feed2Key";
    public static final String FEED3Num = "feed3Num";
    public static final String FEED3Key = "feed3Key";
    private static PreferenceController instance;
    private SharedPreferences preferences;


    public static PreferenceController getInstance(Context context) {
        if (instance == null) instance = new PreferenceController(context, DATABASE_NAME);
        return instance;
    }

    private PreferenceController(Context context, String databaseName) {
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

    public static void alertSound(Context context) {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showKeyboard(Context context, View view) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInputFromWindow(
                    view.getApplicationWindowToken(),
                    InputMethodManager.SHOW_FORCED, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
