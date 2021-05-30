package com.example.visitech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCheckupAdapter extends RecyclerView.Adapter<MyCheckupAdapter.myViewHolder> {
    public List<Checkup> checkups;
    private OnCheckupListener myOnCheckupListener;

    public MyCheckupAdapter(List<Checkup> list, OnCheckupListener onCheckupListener){
        checkups = list;
        myOnCheckupListener = onCheckupListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkup_item, parent,false);
        myViewHolder vh = new myViewHolder(v, myOnCheckupListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Checkup c = checkups.get(position);
        holder.textView.setText(c.toString());
    }

    public interface OnCheckupListener{
        void onCheckupClick(int position);
    }

    @Override
    public int getItemCount() {
        return checkups.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;
        OnCheckupListener onCheckupListener;

        public myViewHolder(@NonNull View itemView, OnCheckupListener onCheckupListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_patient_item);
            this.onCheckupListener = onCheckupListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCheckupListener.onCheckupClick(getAbsoluteAdapterPosition());
        }
    }
}
