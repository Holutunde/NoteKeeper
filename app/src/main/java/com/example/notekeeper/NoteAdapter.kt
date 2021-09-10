package com.example.notekeeper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val context: Context,private val notes : List<NoteInfo>) :
    RecyclerView.Adapter<NoteAdapter.ViewHolder>()  {

    private val inflater : LayoutInflater = LayoutInflater.from(context)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView : View = inflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(itemView)
    }

   override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note : NoteInfo = notes[position]
        holder.courseTitle.text = note.course?.title
        holder.noteTitle.text = note.title
        holder.currentPosition = position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var currentPosition : Int = 0
        var courseTitle : TextView = itemView.findViewById(R.id.text_course_title)
        var noteTitle : TextView = itemView.findViewById(R.id.text_note_title)

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra(NOTE_POSITION, currentPosition)
                context.startActivity(intent)
            }
        }
    }
}