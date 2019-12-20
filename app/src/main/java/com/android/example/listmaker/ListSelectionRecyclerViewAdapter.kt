package com.android.example.listmaker

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.StringBuilder

class ListSelectionRecyclerViewAdapter(private val lists: ArrayList<TaskList>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

        Log.i(TAG, "onCreateViewHolder() called parent; $parent, viewType; $viewType")

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.list_selection_view_holder,
                parent,
                false
            )

        Log.i(TAG, "onCreateViewHolder() called ${ListSelectionViewHolder(view)}")
        return ListSelectionViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.i(TAG,"getItemCount() called Size of list: ${lists.size}")
        return lists.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder called holder: $holder, position: $position")
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = lists[position].name
        Log.i(TAG, "onBindViewHolder called holder pos: ${holder.listPosition.text}, " +
                "title: ${holder.listTitle.text}")
    }

    fun addList(list: TaskList) {
        Log.i(TAG, "addList() called list name: ${list.name}")
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }

    companion object {
        val TAG = ListSelectionRecyclerViewAdapter::class.java.simpleName
    }
}
