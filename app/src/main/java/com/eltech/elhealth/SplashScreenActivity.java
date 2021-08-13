package com.eltech.elhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.eltech.elhealth.Onboarding.HelpActivity;

public class SplashScreenActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 3000;
    private static final String SHARED_PREFS = "sharedPrefs",FINISH = "finish";
    private boolean finished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        finished = sharedPreferences.getBoolean(FINISH, false);
        new Handler().postDelayed(() -> {
            if (finished){
                Intent homeIntent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
            else {
                Intent helpIntent = new Intent(SplashScreenActivity.this, HelpActivity.class);
                startActivity(helpIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
    private long backPressedTime;
    private Toast backToast;
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            finish();
            return;
        }
        else{
            backToast = Toast.makeText(getBaseContext(), getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}
