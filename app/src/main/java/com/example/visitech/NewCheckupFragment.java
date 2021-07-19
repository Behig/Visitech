package com.example.visitech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This fragment adds a new checkup to list of checkups.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class NewCheckupFragment extends Fragment {
    private static final String TAG = "NewCheckupFragment";
    /**
     * List of patients.
     */
    public List<Patient> patients;
    /**
     * Selected patient.
     */
    private Patient patient;

    // Input fields for details of checkup
    private EditText date;
    private EditText doctorFirst;
    private EditText doctorLast;
    private EditText doctorNumber;
    private EditText areas;
    private EditText findings;
    Button saveButton;

    /**
     * Index of the patient on the list of patients.
     */
    int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_new_checkup, container, false);

        // Get the patient from last fragment
        patient = getArguments().getParcelable("selectedPatientNewCheckup");
        // Restore the list of patients
        showListOfPatients();
        Log.d(TAG, "loaded list of size " + patients.size());
        if(patients.size() == 0){
            Log.d(TAG, "loaded list is NULL");
        }
        // Get the Index of the patient on the list of patients
        index = patients.indexOf(patient);

        // Set the details to be saved as the new checkup
        date = (EditText) v.findViewById(R.id.edit_text_date);
        doctorFirst = (EditText) v.findViewById(R.id.edit_text_firstName);
        doctorLast = (EditText) v.findViewById(R.id.edit_text_lastName);
        doctorNumber = (EditText) v.findViewById(R.id.edit_text_personalnumber);
        areas = (EditText) v.findViewById(R.id.edit_text_areas);
        findings = (EditText) v.findViewById(R.id.edit_text_findings);

        saveButton = (Button) v.findViewById(R.id.btn_save_checkup);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Checkup c = makeNewCheckup();
                // Add new checkup to list of checkups of patient
                patient.getCheckups().add(c);
                Log.d(TAG, "index " + index);
                // Set the patient with changes in the list
                patients.set(index, patient);
                // Save the new list again
                saveListOfPatients();
                Log.d(TAG, "save has problem");

                // Go to next fragment and send the checkups and patient and bed number to it
                Fragment checkList = new CheckupListFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("selectedCheckups", (ArrayList<Checkup>)patient.getCheckups());
                bundle.putInt("bedNumber", patient.getBedNr());
                bundle.putParcelable("selectedPatient", patient);
                checkList.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.single_patient_fragment_container, checkList);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

    /**
     * This method creates a new checkup.
     *
     * @return A new checkup.
     */
    private Checkup makeNewCheckup() {
        // Create a new object of type Doctor with inputs
        Doctor d = new Doctor(doctorFirst.getText().toString(), doctorLast.getText().toString(), Integer.parseInt(doctorNumber.getText().toString()));

        Log.d(TAG, "" + date.getText().toString());
        // Set the date of checkup
        String[] cDate = date.getText().toString().split("\\.");
        Log.d(TAG, "" + cDate.length);
        String tmp = cDate[0];
        cDate[0] = cDate[2];
        cDate[2] = tmp;

        // Set the areas
        String cAreas = areas.getText().toString();
        // Set the findings
        String cFindings = findings.getText().toString();

        return new Checkup(cDate, d, cAreas, cFindings);
    }

    /**
     * This method saves the list of patients in shared preferences.
     */
    private void saveListOfPatients(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPatients", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(patients);
        editor.putString("patientList", json);
        editor.apply();
    }

    /**
     * This method restores the list of patients in shared preferences.
     */
    private void showListOfPatients(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPatients", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("patientList", null);
        Type type = new TypeToken<ArrayList<Patient>>(){}.getType();
        patients = gson.fromJson(json, type);

        // If the list in null, we create a new
        if(patients == null){
            Log.d("PatientsActivity", "shit");
            patients = new ArrayList<>();
        }
    }
}