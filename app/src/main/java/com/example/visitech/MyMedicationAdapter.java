package com.example.visitech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyMedicationAdapter extends RecyclerView.Adapter<MyMedicationAdapter.myViewHolder> {
    public List<Medication> medications;
    private OnMedicationListener myOnMedicationListener;

    public MyMedicationAdapter(List<Medication> list, OnMedicationListener onMedicationListener){
        medications = list;
        myOnMedicationListener = onMedicationListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.medication_item, parent,false);
        myViewHolder vh = new myViewHolder(v, myOnMedicationListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Medication m = medications.get(position);
        holder.textView.setText(m.toString());
    }

    public interface OnMedicationListener{
        void onMedicationClick(int position);
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        OnMedicationListener onMedicationListener;

        public myViewHolder(@NonNull View itemView, OnMedicationListener onMedicationListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_medication_item);
            this.onMedicationListener = onMedicationListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMedicationListener.onMedicationClick(getAbsoluteAdapterPosition());
        }
    }
}
