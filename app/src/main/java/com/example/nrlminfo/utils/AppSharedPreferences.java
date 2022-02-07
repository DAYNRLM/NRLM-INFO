package com.example.nrlminfo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSharedPreferences {
    private static AppSharedPreferences appSharedPrefrences;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    private Context context;

    public AppSharedPreferences(Context context) {
        this.appSharedPrefs = context.getSharedPreferences("sharedpref", Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public static AppSharedPreferences getInstance(Context con) {
        if (appSharedPrefrences == null)
            appSharedPrefrences = new AppSharedPreferences(con);
        return appSharedPrefrences;
    }


    public void clearAllData() {
        prefsEditor.clear();
        prefsEditor.commit();
    }

    public void removeKeyData(String key) {
        prefsEditor.remove(key);
        prefsEditor.commit();
    }

    public String getGenOTP() {
        return appSharedPrefs.getString(PrefrenceManager.PREF_KEY_OTP, "");
    }

    public void setGenOTP(String value)
    {
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.PREF_KEY_OTP,value);
        prefsEditor.apply();
    }

    public String getLogin() {
        return appSharedPrefs.getString(PrefrenceManager.PREF_KEY_LOGIN, "");
    }

    public void setLogin(String value)
    {
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.PREF_KEY_LOGIN,value);
        prefsEditor.apply();
    }


    public String getMobileNumber() {
        return appSharedPrefs.getString(PrefrenceManager.PREF_KEY_MOBILE_NUMBER, "");
    }

    public void setMobileNumber(String value)
    {
        this.prefsEditor=appSharedPrefs.edit();
        prefsEditor.putString(PrefrenceManager.PREF_KEY_MOBILE_NUMBER,value);
        prefsEditor.apply();
    }

    public void removeDataAtLogout(){



    }
}
