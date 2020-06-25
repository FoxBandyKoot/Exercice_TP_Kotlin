package com.formationandroid.exercice_tp_kotlin.dagger

import android.app.Application
import com.formationandroid.exercice_tp_kotlin.repository.MainRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(diApplication: Application): Builder?
        fun build(): AppComponent?
    }

    fun inject(mainRepository: MainRepository)

}