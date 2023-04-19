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

import com.example.cuahangbantraicay.Fragment.managerCategory;
//import com.example.cuahangbantraicay.Fragment.managerCreateProduct;
import com.example.cuahangbantraicay.Fragment.managerProduct;
import com.example.cuahangbantraicay.Fragment.managerCategory;
import com.example.cuahangbantraicay.Fragment.managerReveneu;
import com.example.cuahangbantraicay.R;
import com.example.cuahangbantraicay.adapter.AdminProductAdapter;
import com.example.cuahangbantraicay.adapter.CategoryAdapter;
import com.google.android.material.navigation.NavigationView;

public class Admin extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    managerProduct managerproduct = null;

    managerReveneu managerreveneu = null;
    //    managerCreateProduct managerCreateProduct = null;
    managerCategory managerCategory = null;
    AdminProductAdapter adminProductAdapter =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        if (ManagerProductDetail.isActive || ManagerProductCreate.isActive || AdminProductAdapter.isActive) {
            initFragment();
            ManagerProductDetail.isActive = false;
            ManagerProductCreate.isActive = false;
            AdminProductAdapter.isActive = false;

        }
        if (CategoryDetail.isActive || CategoryCreate.isActive || CategoryAdapter.isActive){
            initFragmentCategory();
            CategoryDetail.isActive = false;
            CategoryCreate.isActive= false;
            CategoryAdapter.isActive = false;
        }


        setContentView(R.layout.activity_admin);
        setControl();
        setEvent();
    }

    private void initFragment() {
        managerproduct = new managerProduct();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung, new managerProduct())
                .commit();
    }
    private void initFragmentCategory() {
        managerCategory = new managerCategory();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.NoiDung, new managerCategory())
                .commit();
    }

    private void setEvent() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        banner();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.product:
                        Toast.makeText(Admin.this, "Product", Toast.LENGTH_SHORT).show();
                        if (managerproduct == null) {
                            managerproduct = new managerProduct();
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung, new managerProduct())
                                .commit();
                        break;

                    case R.id.category:
                        Toast.makeText(Admin.this, "create", Toast.LENGTH_SHORT).show();
                        if (managerCategory == null) {
                            managerCategory = new managerCategory();
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung, new managerCategory())
                                .commit();
                        break;
                    case R.id.revenue:
                        Toast.makeText(Admin.this, "Reveneu", Toast.LENGTH_SHORT).show();
                        if (managerreveneu == null) {
                            managerreveneu = new managerReveneu();
                        }
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.NoiDung, new managerReveneu())
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