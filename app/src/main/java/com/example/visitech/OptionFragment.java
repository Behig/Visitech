package com.example.visitech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OptionFragment extends Fragment {
    private static final String TAG = "OptionFragment";
    Intent intent;
    ArrayList<Checkup> checkups;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_option, container,false);

        Button check = (Button) v.findViewById(R.id.diagbtn);
        Button medic = (Button) v.findViewById(R.id.medicbtn);
        TextView bednr = (TextView) v.findViewById(R.id.bedNumber);
        TextView nr = (TextView) v.findViewById(R.id.number);

        Patient patient = getArguments().getParcelable("selectedPatient");
        if(patient == null){
            Log.d(TAG, "patient is NULL");
        }
        checkups = (ArrayList<Checkup>) patient.getCheckups();
        //Toast.makeText(getActivity(), "" + patient.getCheckups().get(0).toString(), Toast.LENGTH_LONG).show();
        Log.d(TAG, "" + checkups.get(0).toString());
        bednr.setText("Bednumber");
        nr.setText(String.format("%d", patient.getBedNr()));

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment culFragment = new CheckupListFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("selectedCheckups", checkups);
                bundle.putInt("bedNumber", patient.getBedNr());
                culFragment.setArguments(bundle);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.single_patient_fragment_container, culFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                //Toast.makeText(getActivity(), "" + patient.getCheckups().get(0).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
