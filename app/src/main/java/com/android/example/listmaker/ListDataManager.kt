package com.android.example.listmaker

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager

class ListDataManager(private val context: Context) {

    fun saveList(list: TaskList) {

        Log.i(TAG, "saveList() called, list: name; ${list.name}, tasks; ${list.tasks}")
        // Save the list name
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
                putStringSet(list.name, list.tasks.toHashSet())
                apply()
            }
    }

    fun readList(): ArrayList<TaskList> {

        Log.i(TAG, "readList() called")

        val sharedPreferencesContext = PreferenceManager
            .getDefaultSharedPreferences(context).all

        val taskLists = ArrayList<TaskList>()

        @Suppress("UNCHECKED_CAST")
        for (taskList: MutableMap.MutableEntry<String, out Any?> in sharedPreferencesContext) {

            val itemHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemHashSet)

            taskLists.add(list)
        }
        Log.i(TAG, "readList() The task list: $taskLists")
        return taskLists
    }

    companion object {
        val TAG by lazy { ListDataManager::class.java.simpleName }
    }
}