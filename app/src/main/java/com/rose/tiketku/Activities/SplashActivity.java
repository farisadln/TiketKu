package com.rose.tiketku.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rose.tiketku.R;

public class SplashActivity extends AppCompatActivity {




    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        getUsernameLocal();


    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if(username_key_new.isEmpty()){

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent gogetstarted = new Intent(SplashActivity.this,GetStartedActivity.class);
                    startActivity(gogetstarted);
                    finish();
                }
            }, 2000);
        }
        else {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent gogethome = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(gogethome);
                    finish();
                }
            }, 2000);
        }
    }
}
