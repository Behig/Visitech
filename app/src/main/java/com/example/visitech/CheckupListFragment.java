package com.example.visitech;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CheckupListFragment extends Fragment implements MyCheckupAdapter.OnCheckupListener{
    private static final String TAG = "CheckupListFragment";
    public List<Checkup> checkups;
    private int bedNr;
    private Patient patient;
    private RecyclerView recyclerView;
    private MyCheckupAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_checkup_list, container, false);
        setHasOptionsMenu(true);

        TextView title = (TextView) v.findViewById(R.id.textViewcheckup);
        bedNr = getArguments().getInt("bedNumber");
        title.setText("Checkups Bednumber " + bedNr);

        patient = getArguments().getParcelable("selectedPatient");

        checkups = getArguments().getParcelableArrayList("selectedCheckups");
        if(checkups == null){
            Log.d(TAG, "checkups is NULL");
        }

        buildRecyclerView(v);

        return v;
    }

    private void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.recyclerviewcheckup);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MyCheckupAdapter(checkups, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment newCheckupFragment = new NewCheckupFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedPatientNewCheckup", patient);
        newCheckupFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.single_patient_fragment_container, newCheckupFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        return true;
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckupClick(int position) {
        Fragment repFragment = new CheckupReportFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedCheckup", checkups.get(position));
        bundle.putParcelable("selectedPatient", patient);
        repFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.single_patient_fragment_container, repFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Log.d(TAG, "onCheckupClick: clicked" + position);
    }
}