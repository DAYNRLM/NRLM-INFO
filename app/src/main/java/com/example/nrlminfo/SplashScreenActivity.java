package com.example.nrlminfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.nrlminfo.ui.home.HomeActivity;
import com.example.nrlminfo.ui.login.AuthActivity;
import com.example.nrlminfo.utils.AppSharedPreferences;

public class SplashScreenActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=4000;
    AppSharedPreferences appSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        appSharedPreferences =AppSharedPreferences.getInstance(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                goToNextScreen();

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

    private void goToNextScreen() {

        if(appSharedPreferences.getLogin().equalsIgnoreCase("ok")){
            Intent i = new Intent(SplashScreenActivity.this, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }else {
            Intent i = new Intent(SplashScreenActivity.this, AuthActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }





    }
}