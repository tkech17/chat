package ge.edu.freeuni.chatserver

import android.app.Application

class App : Application() {


    companion object {
        lateinit var SELF: App
    }

    override fun onCreate() {
        super.onCreate()
        SELF = this
    }

}