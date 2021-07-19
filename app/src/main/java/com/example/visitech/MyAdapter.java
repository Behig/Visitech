package com.example.visitech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

/**
 * An adapter to set up the RecyclerView for the list of patients.
 *
 * This class uses a custom ViewHolder.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    /**
     * List of patients.
     */
    public List<Patient> patients;
    /**
     * An object of type OnPatientListener, which is an interface.
     */
    private OnPatientListener myOnPatientListener;

    /**
     * Constructor of the class.
     * @param list List of patients
     * @param onPatientListener A listener, for the time that we click on patients.
     */
    public MyAdapter(List<Patient> list, OnPatientListener onPatientListener){
        patients = list;
        myOnPatientListener = onPatientListener;
    }

    /**
     * This method creates a view holder for each item on the list.
     *
     * @param parent Parent layout of current layout.
     * @param viewType
     * @return A view holder.
     */
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent,false);
        myViewHolder vh = new myViewHolder(v, myOnPatientListener);
        return vh;
    }

    /**
     * This method binds each patient on the list to a view holder.
     * @param holder A view holder.
     * @param position Position of a patient on the list.
     */
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Patient p = patients.get(position);
        holder.textView.setText(p.toString());
    }

    /**
     * Interface as a listener for clicking on the items of list of patients.
     *
     * The interface contains only one method.
     */
    public interface OnPatientListener{
        /**
         * This method specifies what happens when we click on a patient.
         *
         * @param position Position of the patient on the list.
         */
        void onPatientClick(int position);
    }

    /**
     * This method returns the number of items on the list.
     *
     * @return The number of patients on the list.
     */
    @Override
    public int getItemCount() {
        return patients.size();
    }

    /**
     * A custom view holder.
     */
    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        /**
         * Text on every element of recycler view.
         */
        public TextView textView;
        /**
         * The listener for clicking on patients.
         */
        OnPatientListener onPatientListener;

        /**
         * Constructor of the class.
         *
         * @param itemView An item of the recycler view.
         * @param onPatientListener The listener for clicking on patients.
         */
        public myViewHolder(@NonNull View itemView, OnPatientListener onPatientListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_line);
            this.onPatientListener = onPatientListener;
            itemView.setOnClickListener(this);
        }

        /**
         * The overridden onClick, calls onPatientClick of interface.
         *
         * @param v The layout.
         */
        @Override
        public void onClick(View v) {
            onPatientListener.onPatientClick(getAbsoluteAdapterPosition());
        }
    }
}
