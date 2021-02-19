package com.m2comm.albumtest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.m2comm.albumtest.model.Todo
import com.m2comm.albumtest.repository.TodoRepository

class TodoViewModel(application: Application) : AndroidViewModel(application){

    private val mTodoRepository : TodoRepository
    private var mTodoItems : LiveData<List<Todo>>

    init {
        mTodoRepository = TodoRepository(application)
        mTodoItems = mTodoRepository.getTodoList()
    }

    fun insertTodo (todoModel : Todo) {
        mTodoRepository.insertTodo(todoModel)
    }

    fun getTodoList() : LiveData<List<Todo>> {
        return mTodoItems
    }


}