package com.example.visitech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * An adapter to set up the RecyclerView for the list of medication.
 *
 * This class uses a custom ViewHolder.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class MyMedicationAdapter extends RecyclerView.Adapter<MyMedicationAdapter.myViewHolder> {
    /**
     * List of medications.
     */
    public List<Medication> medications;
    /**
     * An object of type OnMedicationListener, which is an interface.
     */
    private OnMedicationListener myOnMedicationListener;

    /**
     * Constructor of the class.
     *
     * @param list List of medications.
     * @param onMedicationListener  A listener, for the time that we click on medications.
     */
    public MyMedicationAdapter(List<Medication> list, OnMedicationListener onMedicationListener){
        medications = list;
        myOnMedicationListener = onMedicationListener;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_item, parent,false);
        myViewHolder vh = new myViewHolder(v, myOnMedicationListener);
        return vh;
    }

    /**
     * This method binds each medication on the list to a view holder.
     *
     * @param holder A view holder.
     * @param position Position of a medication on the list.
     */
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Medication m = medications.get(position);
        holder.textView.setText(m.toString());
    }

    /**
     * Interface as a listener for clicking on the items of list of medications.
     *
     * The interface contains only one method.
     */
    public interface OnMedicationListener{
        /**
         * This method specifies what happens when we click on a medications.
         *
         * @param position Position of the medication on the list.
         */
        void onMedicationClick(int position);
    }

    /**
     * This method returns the number of items on the list.
     *
     * @return The number of medications on the list.
     */
    @Override
    public int getItemCount() {
        return medications.size();
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
         * The listener for clicking on medications.
         */
        OnMedicationListener onMedicationListener;

        /**
         * Constructor of the class.
         *
         * @param itemView An item of the recycler view.
         * @param onMedicationListener The listener for clicking on medications.
         */
        public myViewHolder(@NonNull View itemView, OnMedicationListener onMedicationListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_medication_item);
            this.onMedicationListener = onMedicationListener;
            itemView.setOnClickListener(this);
        }

        /**
         * The overridden onClick, calls onMedicationClick of interface.
         *
         * @param v The layout.
         */
        @Override
        public void onClick(View v) {
            onMedicationListener.onMedicationClick(getAbsoluteAdapterPosition());
        }
    }
}
