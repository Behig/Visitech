package com.example.visitech;

import android.app.Activity;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This fragment adds a new patient to list of patients.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class NewPatientFragment extends Fragment {
    private static final String TAG = "NewPatientFragment";
    /**
     * List of patients
     */
    public List<Patient> patients;
    private Patient patient;
    private EditText firstName;
    private EditText lastName;
    private EditText birthDate;
    private EditText admissionDate;
    private EditText sex;
    private EditText bedNumber;
    private EditText age;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new, container,false);

        // Restore the list of patients from shared preferences.
        showListOfPatients();

        firstName = (EditText) v.findViewById(R.id.edit_text_newFirstName);
        lastName = (EditText) v.findViewById(R.id.edit_text_newLastName);
        birthDate = (EditText) v.findViewById(R.id.edit_text_newBirthDate);
        admissionDate = (EditText) v.findViewById(R.id.edit_text_newDateOfAdmission);
        sex = (EditText) v.findViewById(R.id.edit_text_sex);
        bedNumber = (EditText) v.findViewById(R.id.edit_text_newBedNumber);
        age = (EditText) v.findViewById(R.id.edit_text_newAge);

        saveButton = (Button) v.findViewById(R.id.btn_save_patient);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient = makeNewPatient();
                // Add the new patient to the list.
                patients.add(patient);
                saveListOfPatients();

                // Go back to PatientsActivity.
                Intent intent = new Intent(getActivity(), PatientsActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        return v;
    }

    /**
     * This method creates a new patient.
     *
     * @return A new patient.
     */
    private Patient makeNewPatient() {
        // Get the content of input fields for the details of patient.
        String pFirstName = firstName.getText().toString();
        String pLastName = lastName.getText().toString();

        String[] pBirthDate = birthDate.getText().toString().split("\\.");
        Log.d(TAG, "" + pBirthDate.length);
        String tmp = pBirthDate[0];
        pBirthDate[0] = pBirthDate[2];
        pBirthDate[2] = tmp;

        String[] pAdmissionDate = admissionDate.getText().toString().split("\\.");
        Log.d(TAG, "" + pAdmissionDate.length);
        tmp = pAdmissionDate[0];
        pAdmissionDate[0] = pAdmissionDate[2];
        pAdmissionDate[2] = tmp;

        String pSex = sex.getText().toString();
        int pBedNumber = Integer.parseInt(bedNumber.getText().toString());
        int pAge = Integer.parseInt(age.getText().toString());

        return new Patient(pFirstName, pLastName, pBirthDate, pAdmissionDate, new ArrayList<Checkup>(), new ArrayList<Medication>(), pSex, pBedNumber, pAge);

    }

    /**
     * This method saves the list of patients in shared preferences.
     */
    private void showListOfPatients(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPatients", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("patientList", null);
        Type type = new TypeToken<ArrayList<Patient>>(){}.getType();
        patients = gson.fromJson(json, type);

        if(patients == null){
            Log.d("PatientsActivity", "shit");
            patients = new ArrayList<>();
        }
    }

    /**
     * This method restores the list of patients in shared preferences.
     */
    private void saveListOfPatients(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPatients", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(patients);
        editor.putString("patientList", json);
        editor.apply();
    }
}
