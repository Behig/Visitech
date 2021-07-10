package com.example.visitech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    Patient patient;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        intent = getIntent();
        medications = intent.getParcelableArrayListExtra("selectedMedications");
        bedNumber = intent.getIntExtra("bedNumber", 0);
        patient = intent.getParcelableExtra("selectedPatient");

        TextView title = (TextView) findViewById(R.id.textViewMedication);
        title.setText("Medications Bednumber " + bedNumber);

        radioGroup = findViewById(R.id.radioGroup);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Fragment mlFragment = new MedicationListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("selectedMedications", (ArrayList<Medication>)medications);
        bundle.putInt("bedNumber", bedNumber);
        mlFragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_medication, mlFragment, "MEDLIST");
        fragmentTransaction.addToBackStack("medlist");
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selecteFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selecteFragment = new HomeFragment();
                            break;
                        case R.id.nav_add:
                            selecteFragment = new NewPatientFragment();
                            break;
                        case R.id.nav_info:
                            selecteFragment = new InfoFragment();
                            break;
                        case R.id.nav_user:
                            selecteFragment = new UserFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_medication_big, selecteFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_medic, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Fragment current = getSupportFragmentManager().findFragmentByTag("MEDLIST");
        if(current != null) {
            Intent intent = new Intent(MedicationActivity.this, SinglePatientActivity.class);
            intent.putExtra("selectedPatient", patient);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MedicationActivity.this, NewMedicationActivity.class);
        intent.putExtra("selectedPatient", patient);
        intent.putExtra("bedNumber", bedNumber);
        startActivity(intent);
        overridePendingTransition(0, 0);
        return true;
    }

    public void buttonCheck(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        if(radioButton.getText().equals("Daily view")){
            Fragment mlFragment = new MedicationDailyListFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("selectedMedications", (ArrayList<Medication>)medications);
            bundle.putInt("bedNumber", bedNumber);
            mlFragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container_medication, mlFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else{
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
    }
}