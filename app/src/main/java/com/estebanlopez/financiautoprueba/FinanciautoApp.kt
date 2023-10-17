package com.estebanlopez.financiautoprueba

import android.app.Application
import com.estebanlopez.financiautoprueba.di.AppContainer

class FinanciautoApp:Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        appContainer = AppContainer()
        super.onCreate()
    }
}