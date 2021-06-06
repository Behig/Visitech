package com.example.visitech;

import android.annotation.SuppressLint;
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

public class CheckupReportFragment extends Fragment {
    private static final String TAG = "CheckupReportFragment";
    private Checkup checkup;
    private Patient patient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_checkup_report, container, false);
        setHasOptionsMenu(true);

        checkup = getArguments().getParcelable("selectedCheckup");
        patient = getArguments().getParcelable("selectedPatient");

        TextView areas = (TextView) v.findViewById(R.id.textViewDescription);
        TextView findings = (TextView) v.findViewById(R.id.textViewFindings);
        TextView doc = (TextView) v.findViewById(R.id.textViewDoc);
        TextView date = (TextView) v.findViewById(R.id.textViewDate);
        areas.setText(checkup.getAreas());
        findings.setText(checkup.getFindings());
        doc.setText("Doctor: " + checkup.getDoctor().getPersonalnumber());
        date.setText("Date: " + checkup.getDate()[2] + "." + checkup.getDate()[1] + "." + checkup.getDate()[0]);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_mail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment emailFragment = new CheckupEmailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("selectedCheckupToMail", checkup);
        bundle.putParcelable("selectedPatientToMail", patient);
        emailFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.single_patient_fragment_container, emailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Log.d(TAG, "on Email Fragment");
        return true;
        //return super.onOptionsItemSelected(item);
    }

}