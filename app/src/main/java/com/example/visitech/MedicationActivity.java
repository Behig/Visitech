package com.example.visitech;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MedicationActivity extends AppCompatActivity {
    Intent intent;
    List<Medication> medications;
    int bedNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        intent = getIntent();
        medications = intent.getParcelableArrayListExtra("selectedMedications");
        bedNumber = intent.getIntExtra("bedNumber", 0);

        TextView title = (TextView) findViewById(R.id.textViewMedication);
        title.setText("Medications Bednumber " + bedNumber);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Fragment mlFragment = new MedicationListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("selectedMedications", (ArrayList<Medication>)medications);
        bundle.putInt("bedNumber", bedNumber);
        mlFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_medication, mlFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectefFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectefFragment = new HomeFragment();
                            break;
                        case R.id.nav_add:
                            selectefFragment = new NewPatientFragment();
                            break;
                        case R.id.nav_info:
                            selectefFragment = new InfoFragment();
                            break;
                        case R.id.nav_user:
                            selectefFragment = new UserFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_medication, selectefFragment).commit();
                    return true;
                }
            };
}