package com.example.opsmobitelkomandroid;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.opsmobitelkomandroid.ModelResponse.Login;

import java.security.NoSuchAlgorithmException;

public class SharedPrefManager {

    Login login;
    private String SHARED_PREF_NAME = "DHANY";
    private SharedPreferences sharedPreferences;

    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUser(Login login){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("idSales", login.getIdSales());
        editor.putString("namaSales", login.getNamaSales());
        editor.putString("namaAgency", login.getNamaAgency());
        editor.putString("idAgency", login.getIdAgency());
        editor.putBoolean("logged", true);
        editor.apply();

    }

    public boolean isLoggedIn(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);

    }

    public Login getLogin() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Login(sharedPreferences.getString("idSales", null),
                sharedPreferences.getString("passSales", null),
                sharedPreferences.getString("namaSales", null),
                sharedPreferences.getString("namaAgency", null),
                sharedPreferences.getString("idAgency", null));
    }

    public void logout(){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
