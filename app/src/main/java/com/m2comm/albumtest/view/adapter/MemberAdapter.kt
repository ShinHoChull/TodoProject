package com.m2comm.albumtest.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.m2comm.albumtest.R
import com.m2comm.albumtest.common.Defines
import com.m2comm.albumtest.model.Todo
import kotlinx.android.synthetic.main.item.view.*

class MemberAdapter(private val lists : List<Todo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    //onclick  구현
    var listener : MemberAdapter.OnMemberItemClickListener? = null

    interface OnMemberItemClickListener {
        fun onMemberItemClick(position : Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item , parent , false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val row = lists[position]
        val viewHolder = holder as ItemViewHolder
        viewHolder.onBind(row)
    }

    override fun getItemCount(): Int {
        return lists.size
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.tv_todo_title
        private val content : TextView= itemView.tv_todo_description
        private val date : TextView = itemView.tv_todo_created_date

        init {
            itemView.setOnClickListener {
                listener?.onMemberItemClick(adapterPosition)
            }
        }

        fun onBind( row : Todo ) {
            title.text = row.title
            content.text = row.description
            date.text = row.createdDate.toDateString("yyyy-mm-dd")
        }
    }

}