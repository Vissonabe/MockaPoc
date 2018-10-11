package com.example.viswanathankp.mockapoc.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreferenceHelper {

  private final String PREF_KEY = "pref_key";

  private SharedPreferences mPreferences;

  public SharePreferenceHelper(Context context){
    mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
  }

  public void setToPreference(){
    SharedPreferences.Editor editor = mPreferences.edit();
    editor.putBoolean(PREF_KEY, true);
    editor.apply();
  }

  public boolean isDbLoaded(){
    return mPreferences.getBoolean(PREF_KEY, false);
  }
}
