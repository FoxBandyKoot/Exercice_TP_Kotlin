package com.formationandroid.exercice_tp_kotlin.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.formationandroid.exercice_tp_kotlin.model.NoteDTO

@Database(entities = [NoteDTO::class], version = 1)

abstract class AppDatabase  : RoomDatabase() {
    abstract fun noteDAO(): NoteDAO?
}