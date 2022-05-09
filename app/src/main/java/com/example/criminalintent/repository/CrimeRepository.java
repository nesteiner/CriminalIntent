package com.example.criminalintent.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.example.criminalintent.dao.CrimeDao;
import com.example.criminalintent.database.CrimeDatabase;
import com.example.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CrimeRepository {
    private static final String DATABASE_NAME = "crime-database";
    private static  CrimeRepository crimeRepository;

    CrimeDatabase database;
    CrimeDao crimeDao;
    Executor executor;

    public static void initialize(Context context) {
        if(crimeRepository == null) {
            crimeRepository = new CrimeRepository(context);
        }
    }

    public static CrimeRepository get() {
        return crimeRepository;
    }

    private CrimeRepository(Context context) {
        database = Room.databaseBuilder(context, CrimeDatabase.class, DATABASE_NAME).build();
        crimeDao = database.crimeDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Crime>> findAll() {
        return crimeDao.findAll();
    }

    public LiveData<Crime> findOne(UUID id) {
        return crimeDao.findOne(id);
    }

    public void updateOne(Crime crime) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crimeDao.updateOne(crime);
            }
        });
    }

    public void insertOne(Crime crime) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                crimeDao.insertOne(crime);
            }
        });
    }
}
