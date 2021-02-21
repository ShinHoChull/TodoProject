package com.m2comm.albumtest.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.m2comm.albumtest.database.TodoDAO
import com.m2comm.albumtest.database.TodoDatabase
import com.m2comm.albumtest.model.Todo

class TodoRepository(application : Application) {

    private var mTodoDatabase : TodoDatabase
    private var mTodoDAO : TodoDAO
    private var mTodoItems : LiveData<List<Todo>>

    init {
        mTodoDatabase = TodoDatabase.getInstance(application)
        mTodoDAO = mTodoDatabase.todoDao()
        mTodoItems = mTodoDAO.getTodoList()
    }

    fun getTodoList() : LiveData<List<Todo>> {
        return mTodoItems
    }

    fun insertTodo(todoModel : Todo) {
        Thread(Runnable {
            mTodoDAO.insertTodo(todoModel)
        }).start()
    }

    fun updateTodo(todoModel: Todo) {
        Thread(Runnable {
            mTodoDAO.updateTodo(todoModel)
        })
    }

}