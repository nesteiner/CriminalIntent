package com.example.criminalintent.model;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class Crime {
    UUID id;
    String title;
    Date date;
    boolean solved;
    int requiresPolice;

    public Crime() {
        id = UUID.randomUUID();
        date = new Date();
    }
}
