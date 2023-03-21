package com.example.cssapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView textView = findViewById(R.id.textsplashscreen);
        //textView.animate().translationX(1300).setDuration(1500).setStartDelay(2500);

        // for moving to the main activity
        Thread thread = new Thread() {

            public void run() {
                try {
                    Thread.sleep(2000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                finally {
                    Intent intent = new Intent(SplashScreen.this, SignInpage.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        thread.start();
    }
}