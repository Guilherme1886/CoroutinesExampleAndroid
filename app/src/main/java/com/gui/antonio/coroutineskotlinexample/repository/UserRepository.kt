package com.gui.antonio.coroutineskotlinexample.repository

import com.gui.antonio.coroutineskotlinexample.App
import com.gui.antonio.coroutineskotlinexample.database.entity.User

class UserRepository {
    fun getAll(): List<User> {
        return App.db.userDao().getAll()
    }

    fun insert(user: User) {
        App.db.userDao().insertUser(user)
    }
}