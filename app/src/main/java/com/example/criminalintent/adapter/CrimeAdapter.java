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
import com.example.criminalintent.util.CrimeSelectedCallback;
import com.example.criminalintent.viewholder.CrimePoliceHolder;
import com.example.criminalintent.viewholder.CrimeViewHolder;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Crime> crimes;
    CrimeSelectedCallback callback;
    public CrimeAdapter(List<Crime> crimes, CrimeSelectedCallback callback) {
        this.crimes = crimes;
        this.callback = callback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if(viewType == 1) {
            final View view = inflater.inflate(R.layout.list_item_crime_police, parent, false);
            return new CrimePoliceHolder(view, callback);
        } else {
            final View view = inflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeViewHolder(view, callback);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Crime crime = crimes.get(position);
        if(holder instanceof CrimeViewHolder) {
            CrimeViewHolder _holder = (CrimeViewHolder) holder;
            _holder.bind(crime);
        } else {
            CrimePoliceHolder _holder = (CrimePoliceHolder) holder;
            _holder.bind(crime);
        }
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    @Override
    public int getItemViewType(int position) {
        return crimes.get(position).getRequiresPolice();
    }
}
