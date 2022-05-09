package com.example.criminalintent.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

@Dao
public interface CrimeDao {
    @Query("select * from crime")
    LiveData<List<Crime>> findAll();

    @Query("select * from crime where id = :id")
    LiveData<Crime> findOne(UUID id);

    @Update
    void updateOne(Crime crime);

    @Insert
    void insertOne(Crime crime);
}
