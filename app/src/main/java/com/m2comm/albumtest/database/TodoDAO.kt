package com.m2comm.albumtest.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.m2comm.albumtest.model.Todo

@Dao
interface TodoDAO {

    @Query("Select * from Todo order by createdDate ASC")
    fun getTodoList() : LiveData<List<Todo>>

    @Insert
    fun insertTodo(todoModel : Todo)

}
