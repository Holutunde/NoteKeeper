package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        initializeDisplayContent()

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


//        val listNotes = findViewById<RecyclerView>(R.id.listNotes)
        // App will crash after adding more notes, cos the list was created with a specific no of notes i.e DM.notes

//        listNotes.adapter = ArrayAdapter(this,
//                android.R.layout.simple_list_item_1,
//                DataManager.notes)

//        listNotes.setOnItemClickListener { parent, view, position, id ->
//            val activityIntent = Intent(this, MainActivity::class.java)
//            activityIntent.putExtra(NOTE_POSITION, position)
//            startActivity(activityIntent)
//        }
//
//
//        val recyclerNotes: RecyclerView = findViewById(R.id.listNotes)
//        recyclerNotes.layoutManager = LinearLayoutManager(this)
    }
        private fun initializeDisplayContent() {
            val layoutManager  = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            val recyclerView = findViewById<RecyclerView>(R.id.listNotes)
            recyclerView.layoutManager = layoutManager

            val adapter = NoteAdapter(this, DataManager.notes)
            recyclerView.adapter = adapter
        }


        override fun onResume() {
            super.onResume()
            val listNotes = findViewById<RecyclerView>(R.id.listNotes)
            listNotes.adapter?.notifyDataSetChanged()
        }
}