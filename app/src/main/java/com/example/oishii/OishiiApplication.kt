package com.example.oishii

import android.app.Application

class OishiiApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        application = this
    }

    companion object {
        lateinit var application: OishiiApplication
    }
}