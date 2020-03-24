package com.gui.antonio.coroutineskotlinexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gui.antonio.coroutineskotlinexample.database.entity.User
import com.gui.antonio.coroutineskotlinexample.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()
    val getAllUsers = liveData(Dispatchers.IO) { emit(userRepository.getAll()) }
    fun insertUser(user: User) = CoroutineScope(Dispatchers.IO).launch { userRepository.insert(user) }
}