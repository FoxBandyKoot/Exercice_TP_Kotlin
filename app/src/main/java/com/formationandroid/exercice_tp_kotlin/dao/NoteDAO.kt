package com.formationandroid.exercice_tp_kotlin.dao

import androidx.room.*
import com.formationandroid.exercice_tp_kotlin.model.NoteDTO
import dagger.Component

@Dao
interface NoteDAO {
    @Query("SELECT * FROM notes")
    fun getListNotes(): MutableList<NoteDTO>

    @Query("SELECT COUNT(*) FROM notes WHERE libelle = :libelle")
     fun countNotesBtLibelle(libelle: String?): Long

    @Insert
     fun insert(vararg notes: NoteDTO?)

    @Update
     fun update(vararg notes: NoteDTO?)

    @Delete
     fun delete(vararg notes: NoteDTO?)
}