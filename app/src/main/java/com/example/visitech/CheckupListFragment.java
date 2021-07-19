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

/**
 * A Fragment to show the list of checkups for a selected patient.
 *
 * This fragment uses a RecyclerView and retrieves the list of checkups from sharedPreferences.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class CheckupListFragment extends Fragment implements MyCheckupAdapter.OnCheckupListener{
    private static final String TAG = "CheckupListFragment";
    /**
     * List of checkups of patient.
     */
    public List<Checkup> checkups;
    /**
     * Bednumber of patient.
     */
    private int bedNr;
    /**
     * Selectes patient
     */
    private Patient patient;
    private RecyclerView recyclerView;
    private MyCheckupAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_checkup_list, container, false);

        // Has this fragment an option menu? -Yes
        setHasOptionsMenu(true);

        // Set the title of page.
        TextView title = (TextView) v.findViewById(R.id.textViewcheckup);
        bedNr = getArguments().getInt("bedNumber");
        title.setText("Checkups Bednumber " + bedNr);

        // Getting the patient from last fragment.
        patient = getArguments().getParcelable("selectedPatient");

        // Getting the list of checkups from last fragment.
        checkups = getArguments().getParcelableArrayList("selectedCheckups");
        if(checkups == null){
            Log.d(TAG, "checkups is NULL");
        }

        // Restore the list of patients.
        buildRecyclerView(v);

        return v;
    }

    /**
     * This method sets up the recyclerView for the items in the list.
     *
     * @param v The layout.
     */
    private void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.recyclerviewcheckup);
        mLayoutManager = new LinearLayoutManager(getContext());
        /**
         * @see MyCheckupAdapter
         */
        adapter = new MyCheckupAdapter(checkups, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    /**
     * This method creates an option menu on the right top of layout.
     *
     * @param menu A menu resource.
     * @param inflater It is used to instantiate menu XML files into Menu objects.
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu, menu);
    }

    /**
     * A method to specify what the selected option does in the menu.
     *
     * In this case, the selected option opens a new fragment to add a new checkup for the patient.
     *
     * @param item Selected menu item.
     * @return Always true.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment newCheckupFragment = new NewCheckupFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedPatientNewCheckup", patient);
        newCheckupFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.single_patient_fragment_container, newCheckupFragment, "NEWCHECKUP");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        return true;
    }

    /**
     * This method shows the detailed report of a checkup in anew fragment after clicking on the checkup.
     *
     * @param position Position of selected checkup in list of checkups of patient.
     */
    @Override
    public void onCheckupClick(int position) {
        Fragment repFragment = new CheckupReportFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedCheckup", checkups.get(position));
        bundle.putParcelable("selectedPatient", patient);
        repFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.single_patient_fragment_container, repFragment, "REPORT");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Log.d(TAG, "onCheckupClick: clicked" + position);
    }
}