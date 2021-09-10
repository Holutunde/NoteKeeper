package com.example.notekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val adapterCourses = ArrayAdapter<CourseInfo>(this,
                android.R.layout.simple_spinner_item,
                DataManager.courses.values.toList()
        )
        val spinnerCourses = findViewById<Spinner>(R.id.spinnerCourses)
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCourses.adapter = adapterCourses

        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET) ?:
                intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

        if (notePosition != POSITION_NOT_SET)
            displayNote()
        else {
            DataManager.notes.add(NoteInfo())
            notePosition = DataManager.notes.lastIndex
        }
    }

    private fun displayNote() {
        val noteText= findViewById<EditText>(R.id.noteText)
        val noteTitle = findViewById<EditText>(R.id.noteTitle)
        val spinnerCourses = findViewById<Spinner>(R.id.spinnerCourses)
        val note = DataManager.notes[notePosition]
        noteTitle.setText(note.title)
        noteText.setText(note.text)
        val coursePosition = DataManager.courses.values.indexOf(note.course)
       spinnerCourses.setSelection(coursePosition)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if(notePosition >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            menuItem?.icon = getDrawable(R.drawable.ic_baseline_block_24)
            menuItem?.isEnabled = false
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun moveNext() {
        ++notePosition
        displayNote()
        invalidateOptionsMenu()
    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(NOTE_POSITION, notePosition)
    }

    private fun saveNote() {
        val noteText = findViewById<EditText>(R.id.noteText)
        val noteTitle = findViewById<EditText>(R.id.noteTitle)
        val spinnerCourses = findViewById<Spinner>(R.id.spinnerCourses)
        val note = DataManager.notes[notePosition]
        note.title = noteTitle.text.toString()
        note.text = noteText.text.toString()
        note.course = spinnerCourses.selectedItem as CourseInfo
    }
}