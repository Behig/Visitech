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

public class NewMedicationFragment extends Fragment {
    private static final String TAG = "NewMedicationFragment";
    public List<Patient> patients;
    private Patient patient;
    private EditText nameOfMedic;
    private EditText dose;
    private EditText day;
    Button saveButton;
    int index = 0;
    int bedNr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_medication, container, false);

        patient = getArguments().getParcelable("selectedPatient");
        bedNr = getArguments().getInt("bedNumber", 0);
        showListOfPatients();
        nameOfMedic = (EditText) v.findViewById(R.id.edit_text_name_medication);
        dose = (EditText) v.findViewById(R.id.edit_text_dose);
        day = (EditText) v.findViewById(R.id.edit_text_day);
        index = patients.indexOf(patient);

        saveButton = (Button) v.findViewById(R.id.btn_save_medication);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Medication m = makeNewMedication();

                patient.getMedications().add(m);
                Log.d(TAG, "index " + index);
                patients.set(index, patient);
                saveListOfPatients();
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

    private Medication makeNewMedication() {
        String fName = nameOfMedic.getText().toString();
        double fDose = Double.parseDouble(dose.getText().toString());
        Day fDay = Day.valueOf(day.getText().toString());

        return new Medication(fName, fDose, fDay);
    }

    private void saveListOfPatients() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPatients", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(patients);
        editor.putString("patientList", json);
        editor.apply();
    }

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
}
