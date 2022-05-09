package com.example.criminalintent.model;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.criminalintent.repository.CrimeRepository;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class CrimeListview extends ViewModel {
    LiveData<List<Crime>> crimes;
    CrimeRepository crimeRepository;
    public CrimeListview() {}

    public void initDatabase(CrimeRepository crimeRepository) {
        this.crimeRepository = crimeRepository;
        Stream.iterate(1, n -> n + 1)
                .limit(100)
                .forEach(i -> {
                    Crime crime = new Crime();
                    crime.setTitle("第" + i + "号违纪");
                    crime.setSolved(i % 2 == 0);

                    if(i % 3 == 0) {
                        crime.setRequiresPolice(1);
                    } else {
                        crime.setRequiresPolice(0);
                    }

                    crimeRepository.insertOne(crime);
                });
    }

    public LiveData<List<Crime>> getCrimes(CrimeRepository crimeRepository) {
        this.crimeRepository = crimeRepository;
        crimes = this.crimeRepository.findAll();
        return crimes;
    }
}
