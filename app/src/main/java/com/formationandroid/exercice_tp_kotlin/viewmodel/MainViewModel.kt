package com.formationandroid.exercice_tp_kotlin.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.formationandroid.exercice_tp_kotlin.model.NoteDTO
import com.formationandroid.exercice_tp_kotlin.repository.MainRepository

/*
        Modèle MVVM -> ViewModel
*/
class MainViewModel: ViewModel() {
    private var mainRepository : MainRepository? = null
    private var liveDataItem : MutableLiveData<NoteDTO>? = null

    fun initialization(mainRepository: MainRepository) {
        if (this.liveDataItem != null) {
            return
        }

        this.mainRepository = mainRepository
        liveDataItem = MutableLiveData()
    }

    fun loadData(context: Context, listNotes: MutableList<NoteDTO>) {
        mainRepository!!.loadData(context, listNotes)
    }
}