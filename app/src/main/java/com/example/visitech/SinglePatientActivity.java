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

/**
 * Activity to show information of single patients (by default the OptionFragment).
 */
public class SinglePatientActivity extends AppCompatActivity {
    Intent intent;
    /**
     * The selcted patient.
     */
    public Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_patient);

        // Set the bottom navigation menu.
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Get patient from last Activity.
        intent = getIntent();
        patient = intent.getParcelableExtra("selectedPatient");

        // Begin the Option Fragment.
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
                        default:
                            selectFragment = new HomeFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, selectFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        // If the back stack has more than 1 element...
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {

            // Get the current fragment on single_patient_fragment_container
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.single_patient_fragment_container);
            if (f instanceof OptionFragment) {
                // Go back to PatientsActivity
                Intent intent = new Intent(this, PatientsActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                return;
            }

            if (f instanceof CheckupListFragment) {
                // Go back to OptionFragment
                Fragment fragment = new OptionFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedPatient", patient);
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, fragment).commit();
                return;
            }

            if (f instanceof NewCheckupFragment) {
                // Go back to CheckupListFragment
                Fragment fragment = new CheckupListFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedPatient", patient);
                bundle.putInt("bedNumber", patient.getBedNr());
                bundle.putParcelableArrayList("selectedCheckups", (ArrayList<? extends Parcelable>) patient.getCheckups());
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, fragment).commit();
                return;
            }

            if (f instanceof CheckupReportFragment) {
                // Go back to CheckupListFragment
                Fragment fragment = new CheckupListFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedPatient", patient);
                bundle.putInt("bedNumber", patient.getBedNr());
                bundle.putParcelableArrayList("selectedCheckups", (ArrayList<? extends Parcelable>) patient.getCheckups());
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.single_patient_fragment_container, fragment).commit();
                return;
            }

            if (f instanceof CheckupEmailFragment) {
                // Just pop the element on the stack.
                getSupportFragmentManager().popBackStack();
                return;
            }
        }
        return;
    }
}