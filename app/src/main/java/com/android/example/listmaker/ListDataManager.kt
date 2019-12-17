package com.android.example.listmaker

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class ListDataManager(private val context: Context) {
    fun saveList(list: TaskList) {
        // Save the list name
        PreferenceManager.getDefaultSharedPreferences(context).edit().apply {
                putStringSet(list.name, list.tasks.toHashSet())
                apply()
            }
    }

    fun readList(): ArrayList<TaskList> {

        val sharedPreferencesContext = PreferenceManager
            .getDefaultSharedPreferences(context).all

        val taskLists = ArrayList<TaskList>()

        @Suppress("UNCHECKED_CAST")
        for (taskList: MutableMap.MutableEntry<String, out Any?> in sharedPreferencesContext) {

            val itemHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemHashSet)

            taskLists.add(list)
        }

        return taskLists
    }
}