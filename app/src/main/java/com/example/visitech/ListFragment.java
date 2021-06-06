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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements MyAdapter.OnPatientListener {
    private static final String TAG = "ListFragment";
    public List<Patient> patients;
    private RecyclerView recyclerView;
    public MyAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container,false);

        showListOfPatients();
        Log.d(TAG, "changing 1");
        /*if(patients == null){
            Log.d(TAG, "changing 4");
        }*/
        //change(patients.get(0));
        Log.d(TAG, "changing 2");
        //showListOfPatients();
        buildRecyclerView(v);

        return v;
    }

    private void saveListOfPatients(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPatients", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(patients);
        editor.putString("patientList", json);
        editor.apply();
    }

    private void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MyAdapter(patients, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
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
            //Patient p = makeSamplePatient();
            //patients.add(p);
            //change(p);
            //saveListOfPatients();
            //adapter.notifyItemInserted(patients.size());
        }
    }

    /*public Patient makeSamplePatient(){
        String[] c = {"1997", "06", "18"};
        Medication m = new Medication("Asta", 23, Day.FRIDAY);
        List<Medication> medic = new ArrayList<>();
        medic.add(m);
        Doctor doc = new Doctor("Gi", "GiGo", 6555);
        Checkup check = new Checkup(c, doc, "fuck", "shit");
        List<Checkup> checks = new ArrayList<>();
        checks.add(check);
        return new Patient("Behnam", "Goudarzi", c, c, checks, medic, "male", 2, 88);
    }*/

    @Override
    public void onPatientClick(int position) {
        Intent intent = new Intent(getActivity(), SinglePatientActivity.class);
        intent.putExtra("selectedPatient", patients.get(position));
        startActivity(intent);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
        //Log.d(TAG, "onPatientClick: clicked" + position);
    }

    /*private void change(Patient p){
        String[] c = {"2021", "06", "18"};
        p.getMedications().set(0, new Medication("Amoxil", 23, Day.FRIDAY));
        p.getCheckups().set(0, new Checkup(c, new Doctor("Max", "Plank", 6555), "Eyes, mouth and thyroid", "Pupils laterally equal in size with inconspicuous direct and\n" +
                "consensual light reaction. Mucous membrane of the mouth pink, tongue moist and without coatings. Nuchal, cervical, axillary and inguinal lymph nodes not enlarged. palpable with normal displacement. Thyroid gland not enlarged palpable, normal displacement during swallowing."));
        Log.d(TAG, "changing 3");
        saveListOfPatients();
    }*/
}
