package com.formationandroid.exercice_tp_kotlin.main

import android.app.Application
import com.formationandroid.exercice_tp_kotlin.dagger.AppComponent
import com.formationandroid.exercice_tp_kotlin.dagger.DaggerAppComponent

class DIApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().application(this)?.build()!!
    }

    companion object {

        private var instance: DIApplication? = null
        // Getter singleton :
        fun getAppComponent(): AppComponent? {
            return instance!!.appComponent
        }
    }

}