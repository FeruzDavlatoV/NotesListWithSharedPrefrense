package com.example.addnotesproject.manager

import android.content.Context
import android.content.SharedPreferences

import com.example.addnotesproject.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type



class SharePref(context: Context) {

    internal val sharedPreferences: SharedPreferences?


    companion object {
        private var preferenceManager: SharePref? = null
        const val KEY = "note"

        fun getInstance(context: Context): SharePref? {
            if (preferenceManager == null) {
                preferenceManager = SharePref(context)
            }
            return preferenceManager
        }
    }

    init {
        sharedPreferences = context.getSharedPreferences("Note", Context.MODE_PRIVATE)
    }

    fun saveData(note: Note) {
        val notes = getData()
        notes.add(note)
        saveData(notes)
    }

    private fun saveData(notes: ArrayList<Note>) {
        val prefsEditor = sharedPreferences!!.edit()
        prefsEditor.putString(KEY,Gson().toJson(notes))
        prefsEditor.apply()
    }

    fun getData(): ArrayList<Note> {
        if (sharedPreferences!!.contains(KEY)) {

            val type: Type = object : TypeToken<ArrayList<Note>>() {}.type
            return Gson().fromJson(sharedPreferences.getString(KEY, ""), type)

        }
        return ArrayList()
    }
}