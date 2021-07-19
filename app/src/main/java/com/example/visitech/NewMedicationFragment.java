package com.example.visitech;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
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
import java.util.function.DoubleUnaryOperator;

/**
 * This fragment adds a new medication to list of medications.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class NewMedicationFragment extends Fragment {
    private static final String TAG = "NewMedicationFragment";
    /**
     * List of patients.
     */
    public List<Patient> patients;
    /**
     * The selected patient.
     */
    private Patient patient;

    // Input fields for details of a medication.
    private EditText nameOfMedic;
    private EditText dose;
    private EditText day;
    Button saveButton;

    /**
     * Index of the patient on the list of patients.
     */
    int index = 0;
    /**
     * Bed number of the patient.
     */
    int bedNr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_medication, container, false);

        // Get the patient and the bedNr from the activity.
        patient = getArguments().getParcelable("selectedPatient");
        bedNr = getArguments().getInt("bedNumber", 0);
        // Restore the list of patients from shared preferences.
        showListOfPatients();

        nameOfMedic = (EditText) v.findViewById(R.id.edit_text_name_medication);
        dose = (EditText) v.findViewById(R.id.edit_text_dose);
        day = (EditText) v.findViewById(R.id.edit_text_day);
        // Find the index of the patient on the list
        index = patients.indexOf(patient);

        saveButton = (Button) v.findViewById(R.id.btn_save_medication);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medication m = makeNewMedication();

                // Add the new medication to list of medications of the patient
                patient.getMedications().add(m);
                Log.d(TAG, "index " + index);
                // Set the changed patient object on the list
                patients.set(index, patient);
                saveListOfPatients();
                //  Go back to the MedicationActivity
                Intent intent = new Intent(getActivity(), MedicationActivity.class);
                intent.putParcelableArrayListExtra("selectedMedications", (ArrayList<? extends Parcelable>) patient.getMedications());
                intent.putExtra("bedNumber", bedNr);
                intent.putExtra("selectedPatient", patient);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        return v;
    }

    /**
     * This method creates a new medication.
     *
     * @return A new medication.
     */
    private Medication makeNewMedication() {
        // Get the content of the input fields.
        String fName = nameOfMedic.getText().toString();
        double fDose = Double.parseDouble(dose.getText().toString());
        Day fDay = Day.valueOf(day.getText().toString());

        return new Medication(fName, fDose, fDay);
    }

    /**
     * This method saves the list of patients in shared preferences.
     */
    private void saveListOfPatients() {
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

        // If the list in null, we create a new.
        if(patients == null){
            Log.d("PatientsActivity", "shit");
            patients = new ArrayList<>();
        }
    }
}
