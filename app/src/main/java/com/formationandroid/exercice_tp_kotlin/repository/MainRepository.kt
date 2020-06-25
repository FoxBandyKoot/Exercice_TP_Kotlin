package com.formationandroid.exercice_tp_kotlin.repository

import android.content.Context
import com.formationandroid.exercice_tp_kotlin.dao.AppDatabaseHelper
import com.formationandroid.exercice_tp_kotlin.dao.NoteDAO
import com.formationandroid.exercice_tp_kotlin.main.DIApplication
import com.formationandroid.exercice_tp_kotlin.model.NoteDTO

import javax.inject.Inject

/*
        Modèle MVVM -> Repository
*/

class MainRepository {

    @Inject
    lateinit var  noteDAO: NoteDAO

    @Inject
    lateinit var applicationContext: Context

    init {
        DIApplication.getAppComponent()?.inject(this)
    }

    fun loadData(context: Context, listNotes: MutableList<NoteDTO>?) {
        listNotes!!.addAll(AppDatabaseHelper.getDatabase(context).noteDAO()?.getListNotes()!!)
    }
}