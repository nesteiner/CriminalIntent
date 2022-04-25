package com.example.criminalintent.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.criminalintent.R;
import com.example.criminalintent.adapter.CrimeAdapter;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.model.CrimeListview;

import java.util.List;

public class CrimeListFragment extends Fragment {
    RecyclerView crimeRecyclerView;
    CrimeAdapter adapter;
    CrimeListview crimeListview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    void updateUI() {
        CrimeListview listview = new CrimeListview();
        List<Crime> crimes = listview.getCrimes();

        if(adapter == null) {
            adapter = new CrimeAdapter(crimes);
            crimeRecyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public static CrimeListFragment getInstance() {
        return new CrimeListFragment();
    }
}
