package com.example.visitech;

import android.content.Context;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ListFragment extends Fragment {
    public List<Patient> patients;
    private RecyclerView recyclerView;
    public myAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container,false);

        showListOfPatients();
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
        adapter = new myAdapter(patients);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void showListOfPatients(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("sharedPatients", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("sharedPatients", null);
        Type type = new TypeToken<ArrayList<Patient>>(){}.getType();
        patients = gson.fromJson(json, type);

        if(patients == null){
            Log.d("PatientsActivity", "shit");
            patients = new ArrayList<>();
            Patient p = makePatient();
            patients.add(0, p);
        }
    }

    public Patient makePatient(){
        Calendar c = new GregorianCalendar(1997, 3, 3);
        Medication m = new Medication("Asta", 23, Day.FRIDAY);
        List<Medication> medic = new ArrayList<>();
        medic.add(m);
        Doctor doc = new Doctor("Gi", "GiGo", 6555);
        Checkup check = new Checkup(c, doc, "fuck", "shit");
        List<Checkup> checks = new ArrayList<>();
        checks.add(check);
        return new Patient("Behnam", "Goudarzi", c, c, checks, medic, "male", 2, 88);
    }
}
