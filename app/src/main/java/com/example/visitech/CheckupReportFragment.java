package com.example.visitech;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckupReportFragment extends Fragment {
    private static final String TAG = "CheckupReportFragment";
    private Checkup checkup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_checkup_report, container, false);

        checkup = getArguments().getParcelable("selectedCheckup");

        TextView description = (TextView) v.findViewById(R.id.textViewDescription);
        TextView pageTitle = (TextView) v.findViewById(R.id.textViewcheckupReport);
        TextView findings = (TextView) v.findViewById(R.id.textViewFindings);
        description.setText(checkup.getDescription());
        findings.setText(checkup.getFindings());
        pageTitle.setText("Checkup " + checkup.toString().toLowerCase());

        return v;
    }

}