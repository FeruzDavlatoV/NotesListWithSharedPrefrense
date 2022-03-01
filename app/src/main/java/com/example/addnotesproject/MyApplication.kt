package com.example.addnotesproject

import android.app.Application
import com.example.addnotesproject.manager.SharePref


import com.example.addnotesproject.model.Note

class MyApplication :Application(){

    companion object {
        val TAG = MyApplication::class.java.simpleName
        var instance: MyApplication? = null
        var notes = ArrayList<Note>()
        internal var sharePref: SharePref? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharePref = SharePref(this)
        notes.addAll(sharePref!!.getData())
    }
}