package com.example.visitech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.single_patient_fragment_container, initFragment, "OPTION");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

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

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {

            Fragment f = getSupportFragmentManager().findFragmentById(R.id.single_patient_fragment_container);
            if (f instanceof OptionFragment) {
                Intent intent = new Intent(this, PatientsActivity.class);
                //getSupportFragmentManager().popBackStack();
                //Toast.makeText(getApplicationContext(),"back to list of patients",Toast.LENGTH_LONG).show();
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return;
            }

            if (f instanceof CheckupListFragment) {
                Fragment fragment = new OptionFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedPatient", patient);
                fragment.setArguments(bundle);
                //Toast.makeText(getApplicationContext(),"to option",Toast.LENGTH_SHORT).show();
                //getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, fragment).commit();
                return;
            }

            if (f instanceof NewCheckupFragment) {
                Fragment fragment = new CheckupListFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedPatient", patient);
                bundle.putInt("bedNumber", patient.getBedNr());
                bundle.putParcelableArrayList("selectedCheckups", (ArrayList<? extends Parcelable>) patient.getCheckups());
                fragment.setArguments(bundle);
                //getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, fragment).commit();
                return;
            }

            if (f instanceof CheckupReportFragment) {
                Fragment fragment = new CheckupListFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedPatient", patient);
                bundle.putInt("bedNumber", patient.getBedNr());
                bundle.putParcelableArrayList("selectedCheckups", (ArrayList<? extends Parcelable>) patient.getCheckups());
                fragment.setArguments(bundle);
                //getSupportFragmentManager().popBackStack();
                getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, fragment).commit();
                return;
            }

            if (f instanceof CheckupEmailFragment) {
                getSupportFragmentManager().popBackStack();
                return;
            }
        }
        return;
    }
}