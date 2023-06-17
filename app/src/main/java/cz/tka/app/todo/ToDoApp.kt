package cz.tka.app.todo

import android.app.Application
import android.content.Context
import cz.tka.app.todo.di.daoModule
import cz.tka.app.todo.di.databaseModule
import cz.tka.app.todo.di.repositoryModule
import cz.tka.app.todo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToDoApp: Application(){
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@ToDoApp)
            modules(
                listOf(
                    daoModule,
                    databaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}
