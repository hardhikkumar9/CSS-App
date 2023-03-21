package com.example.cssapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.cssapplication.R.*;

public class home_page extends AppCompatActivity {

    CardView cardViewhomeform;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button logoutbutton;



    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        setContentView(layout.activity_home_page);

        drawerLayout = findViewById(id.drawerlayoutleft);
        navigationView = findViewById(id.navigationview);
        logoutbutton = findViewById(id.button_logout);

        findViewById(id.sidemenulines).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(id.navigationview);
        navigationView.setItemIconTintList(null);


    }


    public void cardViewhomeform(View v){
        Intent intent = new Intent(this, form_activity.class);
        startActivity(intent);
    }


    public void logoutbutton(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, SignInpage.class);
        startActivity(intent);
    }


}