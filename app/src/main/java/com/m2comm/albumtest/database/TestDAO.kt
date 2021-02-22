package com.m2comm.albumtest.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.m2comm.albumtest.model.Test

@Dao
interface TestDAO {

    @Query("SELECT * from Test limit 1")
    fun getData() : Test

    @Insert
    fun insertTest(test : Test)

    @Update
    fun updateTest(test : Test)

    @Delete
    fun deleteTest(test : Test)

}