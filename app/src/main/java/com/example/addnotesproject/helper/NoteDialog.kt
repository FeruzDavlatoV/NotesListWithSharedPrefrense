package com.example.addnotesproject.helper

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import com.example.addnotesproject.MainActivity
import com.example.addnotesproject.MyApplication
import com.example.addnotesproject.R
import com.example.addnotesproject.model.Note

import java.text.SimpleDateFormat
import java.util.*

class NoteDialog(head:Activity):Dialog(head) {
    private lateinit var et_dialog:EditText
    private lateinit var tv_save:TextView
    private lateinit var tv_cancel:TextView
    private lateinit var date:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.item_dialog)
        initViews()
        setCancelable(true)
    }

    private fun initViews() {
        tv_save = findViewById(R.id.tv_save)
        tv_cancel = findViewById(R.id.tv_cancel)
        et_dialog = findViewById(R.id.et_dialog)

        tv_cancel.setOnClickListener {
            et_dialog.setText("")
            dismiss()
        }
        tv_save.setOnClickListener {
            val simpleDataFrom = SimpleDateFormat("dd-MM-yyyy HH:MM:SS")
            var date = simpleDataFrom.format(Date())
            val note = Note(date,et_dialog.text.toString())
            MyApplication.sharePref!!.saveData(note)
            MainActivity.noteList.add(note)
            dismiss()
        }
    }
}