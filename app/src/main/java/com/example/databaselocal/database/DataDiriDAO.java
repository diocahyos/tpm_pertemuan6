package com.example.databaselocal.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DataDiriDAO {

    @Insert
    Long insertData(DataDiri item);

    @Update
    int updateData(DataDiri item);

    @Delete
    void deleteData(DataDiri item);

    @Query("SELECT * FROM datadiri_db")
    DataDiri[] getData();

}
