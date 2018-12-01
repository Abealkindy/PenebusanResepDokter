package com.rosinante.penebusanresepdokter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {

    private static final String KEY_TOKEN = "tokenLogin";
    private static final String KEY_LOGIN = "isLogin";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private Context c;

    //konstruktor
    @SuppressLint("CommitPrefEdits")
    public UserSession(Context c) {
        this.c = c;
        int PRIVATE_MODE = 0;
        //0 agar cuma bsa dibaca hp itu sendiri
        String PREF_NAME = "ResepPref";
        pref = c.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //membuat session login
    public void createLoginSession(String password) {
        editor.putString(KEY_TOKEN, password);
        editor.putBoolean(KEY_LOGIN, true);
        editor.commit();
        //commit digunakan untuk menyimpan perubahan
    }

    //mendapatkan token
    public String getToken() {
        return pref.getString(KEY_TOKEN, "");
    }

    //cek login
    public boolean isLogin() {
        return pref.getBoolean(KEY_LOGIN, false);
    }

    //logout user
    public void logout() {
        editor.clear();
        editor.commit();
    }

    public void setUsername(String username) {
        editor.putString("username", username);
        editor.commit();
    }

    public String getUsername() {
        return pref.getString("username", "");
    }

    public void setRole(String role) {
        editor.putString("user_role", role);
        editor.commit();
    }

    public String getRole() {
        return pref.getString("user_role", "");
    }

    public void setPassword(String password) {
        editor.putString("user_password", password);
        editor.commit();
    }

    public String getPassword() {
        return pref.getString("user_password", "");
    }

    public void setIduser(String id_user) {
        editor.putString("id_user", id_user);
        editor.commit();
    }

    public String getIdUser() {
        return pref.getString("id_user", "");
    }

}
