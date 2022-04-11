package com.example.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class CrimeLab {
    static CrimeLab crimeLab;
    List<Crime> crimes;

    public static CrimeLab get(Context context) {
        if(crimeLab == null) {
            crimeLab = new CrimeLab(context);
        }

        return crimeLab;
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public Crime getCrime(UUID id) {
        return crimes.stream()
                .filter(crime -> crime.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private CrimeLab(Context context) {
        crimes = new ArrayList<>();
        Stream.iterate(1, i -> i + 1)
                .limit(100)
                .forEach(i -> {
                    Crime crime = new Crime();
                    crime.setTitle("Crime #" + i);
                    crime.setSolved(i % 2 == 0);
                    crimes.add(crime);
                });
    }


}
