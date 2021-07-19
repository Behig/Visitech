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

/**
 * A Fragment to show the list of patients.
 *
 * This fragment uses a RecyclerView and retrieves the list of patients from sharedPreferences.
 *
 * @author Mohammad Goudarzi Moghadam
 */
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

        // Restore the list of patients.
        showListOfPatients();
        Log.d(TAG, "changing 1");
        Log.d(TAG, "changing 2");
        buildRecyclerView(v);

        return v;
    }

    /**
     * This method saves changes on the list of patients back to sharedPreferences.
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
     * This method sets up the recyclerView for the items in the list.
     *
     * @param v The layout.
     */
    private void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MyAdapter(patients, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * This method restores the list of patients from shared preferences.
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
     * This method specifies what will happen, when we click on each patient.
     *
     * In this case we go to the next activity and send the patient to that too.
     *
     * @param position Position of the patient in the list op patients.
     */
    @Override
    public void onPatientClick(int position) {
        Intent intent = new Intent(getActivity(), SinglePatientActivity.class);
        intent.putExtra("selectedPatient", patients.get(position));
        startActivity(intent);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }

}
