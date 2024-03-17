package com.example.catsanddogs

import android.app.Application
import com.google.firebase.FirebaseApp

class CatsAndDogs : Application(){

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
    }
}