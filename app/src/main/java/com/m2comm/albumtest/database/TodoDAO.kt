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
    suspend fun insertTodo(todoModel : Todo)

    @Update
    suspend fun updateTodo(todoModel: Todo)

    @Delete
    suspend fun deleteTodo(todoModel : Todo)


}
