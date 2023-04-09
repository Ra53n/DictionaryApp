package com.example.dictionaryapp

import android.app.Application
import com.example.history_feature.data.di.historyModule
import com.example.main_feature.data.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            androidLogger()
            modules(mainModule, historyModule)
        }
    }
}