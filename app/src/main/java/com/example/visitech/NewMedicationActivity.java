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

/**
 * Activity to create new patients.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class NewMedicationActivity extends AppCompatActivity {
    private static final String TAG = "NewMedicationActivity";
    Intent intent;
    /**
     * The selected patient.
     */
    private Patient patient;
    /**
     * Bed number of the patient.
     */
    int bedNr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medication);

        // Set the bottom navigation menu.
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Get patient and bedNr from the previous activity.
        intent = getIntent();
        patient = intent.getParcelableExtra("selectedPatient");
        bedNr = intent.getIntExtra("bedNumber", 0);

        // Begin the NewMedicationFragment and send patient and bedNr to it.
        Fragment init = new NewMedicationFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedPatient", patient);
        bundle.putInt("bedNumber", bedNr);
        init.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_medication_big_new, init).commit();
    }

    /**
     * This object is a listener for the bottom navigation menu.
     *
     * We can go back to HomeFragment, NewPatientFragment, InfoFragment and UserFragment.
     */
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