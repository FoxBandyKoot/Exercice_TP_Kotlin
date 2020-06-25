package com.formationandroid.exercice_tp_kotlin.model

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.formationandroid.exercice_tp_kotlin.R
import com.formationandroid.exercice_tp_kotlin.view.DetailActivity

class NoteViewHolder(itemView: View, listNotesDTO: List<NoteDTO>) : ViewHolder(itemView) {

    var textViewLibelleNote: TextView = itemView.findViewById(R.id.libelle_note)

    init {
        textViewLibelleNote.setOnClickListener { view ->
            val noteDTO = listNotesDTO[adapterPosition]
            val intent = Intent(view.context, DetailActivity::class.java)
            intent.putExtra("libelle", noteDTO.libelle)
            view.context.startActivity(intent)
        }
    }
}