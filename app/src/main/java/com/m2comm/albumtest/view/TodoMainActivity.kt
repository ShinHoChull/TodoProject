package com.m2comm.albumtest.view

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.m2comm.albumtest.R
import com.m2comm.albumtest.model.Todo
import com.m2comm.albumtest.view.adapter.TodoListAdapter
import com.m2comm.albumtest.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_todo_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import java.util.*
import kotlin.collections.ArrayList

class TodoMainActivity : AppCompatActivity() {

    private lateinit var mTodoListAdapter: TodoListAdapter
    private val mTodoItems: ArrayList<Todo> = ArrayList()
    private lateinit var mTodoViewModel: TodoViewModel

    private val TAG = TodoMainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_main)

        this.initRecyclerView()
        this.initViewModel()
        this.initAddButton()
    }

    private fun initViewModel() {

        mTodoViewModel = ViewModelProvider.AndroidViewModelFactory
            .getInstance(application)
            .create(TodoViewModel::class.java)

        mTodoViewModel.getTodoList()
            .observe(this, androidx.lifecycle.Observer {
                mTodoListAdapter.setTodoItems(it)
            })
    }

    private fun initAddButton() {
        btn_add_todo.setOnClickListener {
            this.openAddTodoDialog(Todo(),false)
        }
    }

    private fun openAddTodoDialog(todoModel: Todo, isModify: Boolean) {
        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        dialogView.et_todo_title.setText(todoModel.title)
        dialogView.et_todo_description.setText(todoModel.description)

        val dialog = AlertDialog.Builder(this)
            .setTitle("추가하기")
            .setView(dialogView)
            .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                val title = dialogView.et_todo_title.text.toString()
                val description = dialogView.et_todo_description.text.toString()
                val createDate = Date().time

                when (isModify) {
                    true -> {
                        todoModel.title = title
                        todoModel.description = description

                        mTodoViewModel.updateTodo(todoModel)
                        mTodoListAdapter.notifyDataSetChanged()
                    }

                    false -> {
                        mTodoViewModel.insertTodo(Todo(null, title, description, createDate))
                    }
                }

            })
            .setNegativeButton("취소", null)
            .create()

        dialog.show()
    }


    private fun initRecyclerView() {

        mTodoListAdapter = TodoListAdapter(mTodoItems).apply {
            listener = object : TodoListAdapter.OnTodoItemClickListener {
                override fun onTodoItemClick(position: Int) {
                    openAddTodoDialog(getItem(position),true)

                }

                override fun onTodoItemLongClick(position: Int) {
                    Toast.makeText(
                        this@TodoMainActivity,
                        "del...",
                        Toast.LENGTH_SHORT
                    ).show()

                    mTodoViewModel.deleteTodo(getItem(position))
                    mTodoListAdapter.notifyDataSetChanged()
                }
            }
        }

        rl_todo_list.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@TodoMainActivity)
            adapter = mTodoListAdapter
        }

    }


}