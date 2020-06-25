package com.formationandroid.exercice_tp_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey


/*
        Modèle MVVM -> Modèle
*/
@Entity(tableName = "notes")
class NoteDTO {

    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0

    var libelle: String? = null
    var libelleTooLong: String? = null


}