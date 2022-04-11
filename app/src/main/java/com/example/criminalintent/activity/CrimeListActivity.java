package com.example.criminalintent.activity;

import androidx.fragment.app.Fragment;
import com.example.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
