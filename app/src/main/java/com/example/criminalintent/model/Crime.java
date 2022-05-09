package com.example.criminalintent.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Crime {
    @PrimaryKey @NonNull UUID id;
    String title;
    Date date;
    boolean solved;
    int requiresPolice;

    public Crime() {
        id = UUID.randomUUID();
        date = new Date();
    }
}
