package com.formationandroid.exercice_tp_kotlin.dao

import android.content.Context
import androidx.room.Room.databaseBuilder


class AppDatabaseHelper private constructor(context: Context) {

    private val database: AppDatabase = databaseBuilder(context, AppDatabase::class.java, "note.db").allowMainThreadQueries().build()

    companion object {
        private var databaseHelper: AppDatabaseHelper? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {

            if (databaseHelper == null) {
                databaseHelper = AppDatabaseHelper(context.applicationContext)
            }
            return databaseHelper!!.database
        }
    }

}
