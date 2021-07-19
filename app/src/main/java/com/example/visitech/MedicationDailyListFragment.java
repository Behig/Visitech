package com.example.visitech;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This fragment shows a list of medications, which the patient should take on that day.
 *
 * This fragment uses a RecyclerView.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class MedicationDailyListFragment extends Fragment implements MyMedicationAdapter.OnMedicationListener{
    private static final String TAG = "MedicationDailyListFragment";
    /**
     * List of all medications.
     */
    public List<Medication> medications;
    /**
     * List of daily medications.
     */
    private List<Medication> medicationsDay;
    /**
     * Bed number of patient.
     */
    private int bedNr;
    private RecyclerView recyclerView;
    private MyMedicationAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_daily_medication_list, container, false);

        // Get the list of all medications from last activity.
        medications = getArguments().getParcelableArrayList("selectedMedications");
        medicationsDay = new ArrayList<>();

        // Get the current weekday.
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        Day dayOfWeek = Day.MONDAY;
        switch (day){
            case Calendar.MONDAY:
                dayOfWeek = Day.MONDAY;
                break;
            case Calendar.TUESDAY:
                dayOfWeek = Day.TUESDAY;
                break;
            case Calendar.WEDNESDAY:
                dayOfWeek = Day.WEDNESDAY;
                break;
            case Calendar.THURSDAY:
                dayOfWeek = Day.THURSDAY;
                break;
            case Calendar.FRIDAY:
                dayOfWeek = Day.FRIDAY;
                break;
            case Calendar.SATURDAY:
                dayOfWeek = Day.SATURDAY;
                break;
            case Calendar.SUNDAY:
                dayOfWeek = Day.SUNDAY;
                break;
        }

        // If the weekday of a medication in medications is same as current weekday, copy it into medicationsDay.
        for(int i = 0; i < medications.size(); i++){
            if(medications.get(i).getWeekDay() == dayOfWeek) {
                medicationsDay.add(medications.get(i));
            }
        }

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
        recyclerView = v.findViewById(R.id.recyclerviewdailymedication);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MyMedicationAdapter(medicationsDay, this);
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