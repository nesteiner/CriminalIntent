package com.example.criminalintent.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.criminalintent.R;
import com.example.criminalintent.model.Crime;

public class CrimeFragment extends Fragment {
    Crime crime;
    EditText titleField;
    Button dateButton;
    CheckBox solvedCheckbox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_crime, container, false);
        titleField = view.findViewById(R.id.crime_title);
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
        solvedCheckbox.setOnCheckedChangeListener((buttonview, isChecked) -> {
            crime.setSolved(isChecked);
        });

        return view;
    }
}
