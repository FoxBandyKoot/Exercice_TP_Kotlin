package com.formationandroid.exercice_tp_kotlin.dagger

import android.app.Application
import android.content.Context
import com.formationandroid.exercice_tp_kotlin.dao.AppDatabaseHelper
import com.formationandroid.exercice_tp_kotlin.dao.NoteDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideNoteDAO(context: Context): NoteDAO
    {
        return AppDatabaseHelper.getDatabase(context).noteDAO()!!
    }
}