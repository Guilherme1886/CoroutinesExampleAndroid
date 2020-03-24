package com.gui.antonio.coroutineskotlinexample

import android.app.Application
import androidx.room.Room
import com.gui.antonio.coroutineskotlinexample.database.AppDatabase

class App : Application() {
    companion object {
        lateinit var db: AppDatabase
    }
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "db_app").build()
    }
}