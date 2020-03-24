package com.gui.antonio.coroutineskotlinexample.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gui.antonio.coroutineskotlinexample.database.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert
    fun insertUser(user: User)

}