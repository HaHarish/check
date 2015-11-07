package com.nidarooms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.nidarooms.login.LoginActivity;

/**
 * The SplashActivity class displays the app logo.
 *
 * @author Harish
 * @version 1.0 03/11/15
 */
public class SplashActivity extends AppCompatActivity {

    /** Set Time out for Splash Activity */
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // To Start Handler for Splash Screen Time out
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent intentLoginActivity = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intentLoginActivity);

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                finish(); // Minimize the Splash Screen

            }
        }, SPLASH_TIME_OUT); // Time out from Splash Screen
    }
}