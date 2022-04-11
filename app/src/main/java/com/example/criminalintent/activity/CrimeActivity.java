package com.example.criminalintent.activity;

import androidx.fragment.app.Fragment;
import com.example.criminalintent.fragment.CrimeFragment;

public class CrimeActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
