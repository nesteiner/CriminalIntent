package com.example.criminalintent.model;

import android.util.Log;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class CrimeListview {
    List<Crime> crimes;

    public CrimeListview() {
        crimes = new ArrayList<>();
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

                    crimes.add(crime);
                });
    }
}
