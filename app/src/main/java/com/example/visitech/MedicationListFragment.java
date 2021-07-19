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

/**
 * This fragment shows a list of all medications.
 *
 * This fragment uses a RecyclerView.
 *
 * @author Mohammad Goudarzi Moghadam
 */
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

        // Get the list of all medications from last activity.
        medications = getArguments().getParcelableArrayList("selectedMedications");
        if(medications == null){
            Log.d(TAG, "medications is NULL");
        }

        buildRecyclerView(v);

        return v;
    }

    /**
     * This method sets up the recyclerView for the items in the list.
     *
     * @param v The layout.
     */
    private void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.recyclerviewmedication);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MyMedicationAdapter(medications, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * If we click on a medication, nothing happens.
     * @param position Position of a medication in the list.
     */
    @Override
    public void onMedicationClick(int position) {
        Log.d(TAG, "onMedicationClick: clicked " + position);
    }
}