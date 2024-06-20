package com.example.challenge.presentation.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inicialize o Koin
        startKoin {
            // Fornecer o contexto Android
            androidContext(this@MyApp)
            modules(myModule)
        }
    }
}
