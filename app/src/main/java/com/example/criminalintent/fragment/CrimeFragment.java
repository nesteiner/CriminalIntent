package com.example.criminalintent.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;
import com.example.criminalintent.model.CrimeDetailView;
import com.example.criminalintent.util.DatePickerCallback;

import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment implements DatePickerCallback {
    static final String TAG = "CrimeFragment";
    static final String DIALOG_DATE = "DialogDate";
    static final String ARG_CRIME_ID = "crime_id";
    static final int REQUEST_DATE = 0;
    Crime crime;
    EditText titleField;
    Button dateButton;
    CheckBox solvedCheckbox;
    CrimeDetailView crimeDetailView;
    LiveData<Crime> crimeLiveData;

    public static CrimeFragment getInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        Log.d(TAG, "args bundle crime id is: " + crimeID);
        crime = new Crime();
        crimeDetailView = new ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()
                )).get(CrimeDetailView.class);
        crimeLiveData = crimeDetailView.loadCrime(crimeID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crime, container, false);
        titleField = view.findViewById(R.id.crime_title);
        titleField.setText(crime.getTitle());
        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                crime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dateButton = view.findViewById(R.id.crime_date);
        dateButton.setTag(crime.getDate().toString());
        dateButton.setEnabled(false);

        solvedCheckbox = view.findViewById(R.id.crime_solved);
        solvedCheckbox.setChecked(crime.isSolved());
        solvedCheckbox.setOnCheckedChangeListener((buttonview, isChecked) -> {
            crime.setSolved(isChecked);
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dateButton.setOnClickListener(view -> {
            DatePickerFragment datePickerFragment = DatePickerFragment.getInstance(crime.getDate());
            datePickerFragment.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
            datePickerFragment.show(getParentFragment().getFragmentManager(), DIALOG_DATE);
        });

        titleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                crime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        crimeLiveData.observe(getViewLifecycleOwner(), _crime -> {
            this.crime = _crime;
            updateUI();
        });
    }

    private void updateUI() {
        titleField.setText(crime.getTitle());
        dateButton.setText(crime.getDate().toString());
        solvedCheckbox.setChecked(crime.isSolved());
    }

    @Override
    public void onStop() {
        super.onStop();
        crimeDetailView.saveCrime(crime);
    }

    @Override
    public void onDateSelected(Date date) {
        crime.setDate(date);
        updateUI();
    }
}
