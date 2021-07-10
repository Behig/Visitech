package com.example.visitech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NewMedicationActivity extends AppCompatActivity {
    private static final String TAG = "NewMedicationActivity";
    Intent intent;
    private Patient patient;
    int bedNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        intent = getIntent();
        patient = intent.getParcelableExtra("selectedPatient");
        bedNr = intent.getIntExtra("bedNumber", 0);

        Fragment init = new NewMedicationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedPatient", patient);
        bundle.putInt("bedNumber", bedNr);
        init.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_medication_big_new, init).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectFragment = null;

                    switch (item.getItemId()){
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
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_medication_big_new, selectFragment).commit();
                    Log.d(TAG, "changing fragment");
                    return true;
                }
            };
}