package com.m2comm.albumtest.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import com.m2comm.albumtest.database.TodoDAO
import com.m2comm.albumtest.database.TodoDatabase
import com.m2comm.albumtest.model.Test
import com.m2comm.albumtest.model.Todo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
        GlobalScope.launch(Dispatchers.IO) {
            mTodoDAO.insertTodo(todoModel)
        }

    }

    fun updateTodo(todoModel: Todo) {
        GlobalScope.launch(Dispatchers.IO) {
            mTodoDAO.updateTodo(todoModel)
        }

    }

    fun deleteTodo(todoModel : Todo) {
        GlobalScope.launch(Dispatchers.IO) {
            mTodoDAO.deleteTodo(todoModel)
        }

    }

}