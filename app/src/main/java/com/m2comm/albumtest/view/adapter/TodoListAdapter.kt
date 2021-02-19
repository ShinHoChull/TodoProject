package com.m2comm.albumtest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.m2comm.albumtest.R
import com.m2comm.albumtest.model.Todo

class TodoListAdapter(val todoItems : ArrayList<Todo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item ,
        parent , false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val todoModel = todoItems[position]
        val todoViewHolder = holder as TodoViewHolder
        todoViewHolder.bind(todoModel)
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}

class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(todoModel : Todo) {

    }
}