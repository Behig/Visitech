package com.example.visitech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SinglePatientActivity extends AppCompatActivity {
    Intent intent;
    public Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_patient);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        intent = getIntent();
        patient = intent.getParcelableExtra("selectedPatient");

        Fragment initFragment = new OptionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedPatient", patient);
        initFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, initFragment).commit();

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
                        default:
                            selectFragment = new HomeFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, selectFragment).commit();
                    return true;
                }
            };
}