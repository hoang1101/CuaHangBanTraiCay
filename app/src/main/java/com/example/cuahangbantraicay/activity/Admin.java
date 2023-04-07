package com.example.cuahangbantraicay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.cuahangbantraicay.Fragment.managerProduct;
import com.example.cuahangbantraicay.Fragment.managerReveneu;
import com.example.cuahangbantraicay.R;
import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    managerProduct managerproduct = null;

    managerReveneu managerreveneu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin);
        setControl();
        setEvent();
    }

    private void setEvent() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        banner();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.product:
                        Toast.makeText(Admin.this, "Product", Toast.LENGTH_SHORT).show();
                        if (managerproduct ==null) {
                            managerproduct = new managerProduct();
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,new managerProduct())
                                .commit();
                        break;
                    case R.id.revenue:
                        Toast.makeText(Admin.this, "Reveneu", Toast.LENGTH_SHORT).show();
                        if (managerreveneu ==null) {
                            managerreveneu = new managerReveneu();
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung,new managerReveneu())
                                .commit();
                        break;
                }
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void banner() {

    }

    private void setControl() {
        drawerLayout = findViewById(R.id.draw);
        navigationView = findViewById(R.id.navigate);
        viewFlipper = findViewById(R.id.viewflipper);
    }


}