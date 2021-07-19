package com.example.visitech;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * An adapter to set up the RecyclerView for the list of checkups.
 *
 * This class uses a custom ViewHolder.
 *
 * @author Mohammad Goudarzi Moghadam
 */
public class MyCheckupAdapter extends RecyclerView.Adapter<MyCheckupAdapter.myViewHolder> {
    /**
     * List of checkups.
     */
    public List<Checkup> checkups;
    /**
     * An object of type OnCheckupListener, which is an interface.
     */
    private OnCheckupListener myOnCheckupListener;

    /**
     * Constructor of the class.
     *
     * @param list List of checkups.
     * @param onCheckupListener A listener, for the time that we click on checkups.
     */
    public MyCheckupAdapter(List<Checkup> list, OnCheckupListener onCheckupListener){
        checkups = list;
        myOnCheckupListener = onCheckupListener;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkup_item, parent,false);
        myViewHolder vh = new myViewHolder(v, myOnCheckupListener);
        return vh;
    }

    /**
     * This method binds each checkup on the list to a view holder.
     *
     * @param holder A view holder.
     * @param position Position of a checkup on the list.
     */
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Checkup c = checkups.get(position);
        holder.textView.setText(c.toString());
    }

    /**
     * Interface as a listener for clicking on the items of list of checkups.
     *
     * The interface contains only one method.
     */
    public interface OnCheckupListener{
        /**
         * This method specifies what happens when we click on a checkup.
         *
         * @param position Position of the checkup on the list.
         */
        void onCheckupClick(int position);
    }

    /**
     * This method returns the number of items on the list.
     *
     * @return The number of checkups on the list.
     */
    @Override
    public int getItemCount() {
        return checkups.size();
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
         * The listener for clicking on checkups.
         */
        OnCheckupListener onCheckupListener;

        /**
         * Constructor of the class.
         *
         * @param itemView An item of the recycler view.
         * @param onCheckupListener The listener for clicking on checkups.
         */
        public myViewHolder(@NonNull View itemView, OnCheckupListener onCheckupListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textview_patient_item);
            this.onCheckupListener = onCheckupListener;
            itemView.setOnClickListener(this);
        }

        /**
         * The overridden onClick, calls onCheckupClick of interface.
         *
         * @param v The layout.
         */
        @Override
        public void onClick(View v) {
            onCheckupListener.onCheckupClick(getAbsoluteAdapterPosition());
        }
    }
}
