package com.m2comm.albumtest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.m2comm.albumtest.R
import com.m2comm.albumtest.model.Todo
import kotlinx.android.synthetic.main.item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodoListAdapter(var todoItems: ArrayList<Todo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener : OnTodoItemClickListener? = null

    //onclick  구현
    interface OnTodoItemClickListener {
        fun onTodoItemClick(position : Int)
        fun onTodoItemLongClick(position : Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item,
            parent, false
        )
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

    fun getItem(position: Int) : Todo = todoItems[position]

    fun setTodoItems(todoItems : List<Todo>) {
        this.todoItems = todoItems as ArrayList<Todo>
        notifyDataSetChanged()
    }

    fun addItem(todoModel : Todo) {
        todoItems.add(todoModel)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.tv_todo_title
        val description = itemView.tv_todo_description
        val createdData = itemView.tv_todo_created_date


        init {
            itemView.setOnClickListener{
                listener?.onTodoItemClick(adapterPosition)
            }

            itemView.setOnLongClickListener {
                listener?.onTodoItemLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }

        fun bind(todoModel: Todo) {
            title.text = todoModel.title
            description.text = todoModel.description
            createdData.text = todoModel.createdDate.toDateString("yyyy.MM.dd HH:mm")
        }
    }

}



//extension 기능.
fun Long.toDateString(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format((Date(this)))
}

