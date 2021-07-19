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

/**
 * This is the activity which contains MedicationListFragment and also MedicationDailyListFragment.
 *
 * This activity uses a radio button, which can be changed, depending on whether we want to see the
 * medication of that day or all medications.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class MedicationActivity extends AppCompatActivity {
    Intent intent;
    /**
     * Lst of medications for a the selected patient.
     */
    List<Medication> medications;
    /**
     * Bednumber of the selected patient.
     */
    int bedNumber;
    /**
     * The selected patient.
     */
    Patient patient;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        // Get the medications, bedNumber and patient from last fragment.
        intent = getIntent();
        medications = intent.getParcelableArrayListExtra("selectedMedications");
        bedNumber = intent.getIntExtra("bedNumber", 0);
        patient = intent.getParcelableExtra("selectedPatient");

        // Set the title of activity.
        TextView title = (TextView) findViewById(R.id.textViewMedication);
        title.setText("Medications Bednumber " + bedNumber);

        // Set the radio group.
        radioGroup = findViewById(R.id.radioGroup);

        // Set the bottom navigation menu.
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Create a MedicationListFragment and send list of medications and patient and bed number
        // to it.
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

    /**
     * This object is a listener for the bottom navigation menu.
     *
     * We can go back to HomeFragment, NewPatientFragment, InfoFragment and UserFragment.
     */
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

    /**
     * This method creates an option menu on the right top of layout.
     *
     * @param menu A menu resource.
     * @return Always true.
     */
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu_medic, menu);
        return true;
    }

    /**
     * When we press back, we check if the MedicationLitFragment is being shown.
     * If true, we go back to SinglePatientActivity.
     */
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

    /**
     * A method to specify what the selected option does in the menu.
     *
     * In this case, the selected option opens a new fragment add a new medication for the patient.
     *
     * @param item Selected menu item.
     * @return Always true.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MedicationActivity.this, NewMedicationActivity.class);
        intent.putExtra("selectedPatient", patient);
        intent.putExtra("bedNumber", bedNumber);
        startActivity(intent);
        overridePendingTransition(0, 0);
        return true;
    }

    /**
     * This method checks, which radio button is active and changes the fragment accordingly.
     * We can change between daily medication and all medication.
     * In both cases we send selected medications and bed number to them.
     *
     * @param v The layout of Activity.
     */
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