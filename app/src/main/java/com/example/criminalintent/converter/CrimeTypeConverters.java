package com.example.criminalintent.converter;

import androidx.room.TypeConverter;

import java.util.Date;
import java.util.UUID;

public class CrimeTypeConverters {
    @TypeConverter
    public long fromDate(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public Date toDate(long millisSinceEpoch) {
        return new Date(millisSinceEpoch);
    }

    @TypeConverter
    public String fromUUID(UUID uuid) {
        return uuid.toString();
    }

    @TypeConverter
    public UUID toUUID(String uuid) {
        return UUID.fromString(uuid);
    }
}
