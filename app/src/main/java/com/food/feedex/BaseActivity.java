package com.food.feedex;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.food.Utils.Constants;
import com.food.Utils.MyContextWrapper;
import com.food.Utils.PreferenceController;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String lang;
        try {
            if (!PreferenceController.getInstance(this).get(PreferenceController.LANGUAGE).equals(Constants.ARABIC)) {
                PreferenceController.getInstance(this).persist(PreferenceController.LANGUAGE, Constants.ENGLISH);
                lang = Constants.ENGLISH;
            } else {
                lang = Constants.ARABIC;
            }
        } catch (Exception e) {
            lang = Constants.ENGLISH;
        }
        super.attachBaseContext(MyContextWrapper.wrap(newBase, new Locale(lang)));
    }


}
