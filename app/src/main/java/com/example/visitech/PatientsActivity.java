package com.example.visitech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class PatientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.patient_fragment_container, new ListFragment(), "LIST").commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectFragment = new HomeFragment();
                            break;
                        case R.id.nav_add:
                            selectFragment = new NewPatientFragment();
                            break;
                        case R.id.nav_info:
                            selectFragment = new InfoFragment();
                            break;
                        case R.id.nav_user:
                            selectFragment = new UserFragment();
                            break;
                        default:
                            selectFragment = new HomeFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.patient_fragment_container, selectFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        Fragment current = getSupportFragmentManager().findFragmentByTag("LIST");
        if(current != null){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
            overridePendingTransition(0, 0);
        }
    }
}