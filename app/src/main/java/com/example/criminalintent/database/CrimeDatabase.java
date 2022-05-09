package com.example.criminalintent.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.criminalintent.converter.CrimeTypeConverters;
import com.example.criminalintent.dao.CrimeDao;
import com.example.criminalintent.model.Crime;

@Database(entities = {Crime.class}, version = 1, exportSchema = false)
@TypeConverters(CrimeTypeConverters.class)
public abstract class CrimeDatabase extends RoomDatabase {
    public abstract CrimeDao crimeDao();
}
