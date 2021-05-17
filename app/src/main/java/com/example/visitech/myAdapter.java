package com.example.visitech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.*;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {
    public List<Patient> patients;

    public myAdapter(List<Patient> list){
        patients = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_item, parent,false);
        myViewHolder vh = new myViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Patient p = patients.get(position);
        holder.textView.setText(p.toString());
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_line);
        }
    }
}
