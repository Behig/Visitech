package com.example.visitech;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MedicationListFragment extends Fragment implements MyMedicationAdapter.OnMedicationListener{
    private static final String TAG = "MedicationListFragment";
    public List<Medication> medications;
    private int bedNr;
    private RecyclerView recyclerView;
    private MyMedicationAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_all_medication_list, container, false);
        //setHasOptionsMenu(true);

        medications = getArguments().getParcelableArrayList("selectedMedications");
        if(medications == null){
            Log.d(TAG, "medications is NULL");
        }

        buildRecyclerView(v);

        return v;
    }

    private void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.recyclerviewmedication);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MyMedicationAdapter(medications, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onMedicationClick(int position) {
        Log.d(TAG, "onMedicationClick: clicked " + position);
    }
}