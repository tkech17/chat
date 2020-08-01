package ge.edu.freeuni.chat

import android.app.Application

class App : Application() {

    companion object {
        lateinit var self: App
    }

    override fun onCreate() {
        super.onCreate()
        self = this
    }


}