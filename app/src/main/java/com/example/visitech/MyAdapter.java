package com.example.visitech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    public List<Patient> patients;
    private OnPatientListener myOnPatientListener;

    public MyAdapter(List<Patient> list, OnPatientListener onPatientListener){
        patients = list;
        myOnPatientListener = onPatientListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent,false);
        myViewHolder vh = new myViewHolder(v, myOnPatientListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Patient p = patients.get(position);
        holder.textView.setText(p.toString());
    }

    public interface OnPatientListener{
        void onPatientClick(int position);
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        OnPatientListener onPatientListener;

        public myViewHolder(@NonNull View itemView, OnPatientListener onPatientListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_line);
            this.onPatientListener = onPatientListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPatientListener.onPatientClick(getAbsoluteAdapterPosition());
        }
    }
}
