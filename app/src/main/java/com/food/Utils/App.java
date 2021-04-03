package com.food.Utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;


import java.util.Locale;

import static com.food.Utils.Constants.ARABIC;
import static com.food.Utils.Constants.ENGLISH;


public class App extends Application {
    private static App mContext;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (!PreferenceController.getInstance(this).get(PreferenceController.LANGUAGE).equals(ENGLISH)) {
            LanguageUtil.changeLanguageType(mContext, new Locale(ARABIC));

        } else {
            LanguageUtil.changeLanguageType(mContext, Locale.ENGLISH);

        }

    }

    public static Context getContext() {
        return mContext;
    }
}
