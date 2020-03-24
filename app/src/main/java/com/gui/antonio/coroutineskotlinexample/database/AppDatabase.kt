package com.gui.antonio.coroutineskotlinexample.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gui.antonio.coroutineskotlinexample.database.entity.User
import com.gui.antonio.coroutineskotlinexample.database.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}