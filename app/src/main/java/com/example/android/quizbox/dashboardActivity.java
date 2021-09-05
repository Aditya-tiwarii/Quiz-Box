package com.example.android.quizbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class dashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_profile:
                Intent intent = new Intent(dashboardActivity.this,profileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_insta:
                Uri uri = Uri.parse("https://www.instagram.com/aditya.tiwariii/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/aditya.tiwariii/")));
                }
                break;
            case R.id.nav_mail:
                Intent intent1 = new Intent(dashboardActivity.this,emailActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_call:
                Intent intent2 = new Intent(dashboardActivity.this,callActivity.class);
                startActivity(intent2);
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void profile(View view) {
        Intent intent = new Intent(dashboardActivity.this,profileActivity.class);
        startActivity(intent);
    }

    public void startbtn(View view) {
        start= findViewById(R.id.startbtn);
        start.setVisibility(View.VISIBLE);

    }

    public void quiz(View view) {
        Intent intent2 = new Intent(dashboardActivity.this,quizActivity.class);
        startActivity(intent2);

    }
}