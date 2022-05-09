package com.example.criminalintent.application;

import android.app.Application;
import com.example.criminalintent.repository.CrimeRepository;

public class CriminalIntentAppllication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrimeRepository.initialize(this);
    }
}
