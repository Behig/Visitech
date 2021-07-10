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

public class MedicationDailyListFragment extends Fragment implements MyMedicationAdapter.OnMedicationListener{
    private static final String TAG = "MedicationDailyListFragment";
    public List<Medication> medications;
    private List<Medication> medicationsDay;
    private int bedNr;
    private RecyclerView recyclerView;
    private MyMedicationAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_daily_medication_list, container, false);
        //setHasOptionsMenu(true);

        medications = getArguments().getParcelableArrayList("selectedMedications");
        medicationsDay = new ArrayList<>();
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

    private void buildRecyclerView(View v){
        recyclerView = v.findViewById(R.id.recyclerviewdailymedication);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter = new MyMedicationAdapter(medicationsDay, this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /*@Override
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
    }*/

    @Override
    public void onMedicationClick(int position) {
        Log.d(TAG, "onMedicationClick: clicked " + position);
    }
}