package com.bahri.simaling;


import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String INTRO = "intro";
    private final String NOKTP = "nik";
    private final String NOTELP = "hobby";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }

    public void putNoktp(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NOKTP, loginorout);
        edit.commit();
    }
    public String getNoktp() {
        return app_prefs.getString(NOKTP, "");
    }

    public void putNotelp(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NOTELP, loginorout);
        edit.commit();
    }
    public String getHobby() {
        return app_prefs.getString(NOTELP, "");
    }

 }
