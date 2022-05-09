package com.example.criminalintent.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.criminalintent.R;
import com.example.criminalintent.adapter.CrimeAdapter;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.model.CrimeListview;
import com.example.criminalintent.repository.CrimeRepository;

import java.util.List;
import java.util.UUID;

public class CrimeListFragment extends Fragment {
    private static final String TAG = "CrimeListFragment";
    RecyclerView crimeRecyclerView;
    CrimeAdapter adapter;
    CrimeListview crimeListview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crimeListview = new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getActivity().getApplication()))
                .get(CrimeListview.class);

        crimeListview.initDatabase(CrimeRepository.get());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // updateUI();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateUI();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    void updateUI() {
        crimeListview.getCrimes(CrimeRepository.get())
                .observe(getViewLifecycleOwner(), new Observer<List<Crime>>() {
                    @Override
                    public void onChanged(List<Crime> crimes) {
                        adapter = new CrimeAdapter(crimes);
                        crimeRecyclerView.setAdapter(adapter);
                    }
                });
    }

    public static CrimeListFragment getInstance() {
        return new CrimeListFragment();
    }
}
