package com.example.cssapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class queries_menu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queries_menu);
    }

    public void queriehomebutton(View view) {
        Intent intent = new Intent(this, home_page.class);
        startActivity(intent);
    }

}