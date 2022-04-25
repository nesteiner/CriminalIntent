package com.example.criminalintent.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.viewholder.CrimeViewHolder;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeViewHolder> {
    List<Crime> crimes;
    public CrimeAdapter(List<Crime> crimes) {
        this.crimes = crimes;
    }

    @NonNull
    @Override
    public CrimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.list_item_crime, parent, false);
        return new CrimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CrimeViewHolder holder, int position) {
        Crime crime = crimes.get(position);
        holder.bind(crime);

        Log.d("on Bind ViewHolder", crime.toString());
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }
}
