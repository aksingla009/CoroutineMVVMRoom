package com.aman.roommvvmcoroutinenavigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aman.roommvvmcoroutinenavigation.model.LoginState
import com.aman.roommvvmcoroutinenavigation.model.User
import com.aman.roommvvmcoroutinenavigation.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun signup(username: String, password: String, info: String) {
        coroutineScope.launch {
            val userFromDB = db.getUser(username)
            if (userFromDB != null) {
                withContext(Dispatchers.Main) {
                    error.value = "User Already Exists"
                }
            } else {
                val userToBeInserted = User(username, password.hashCode(), info)
                val userId = db.insertUser(userToBeInserted)
                userToBeInserted.id = userId
                LoginState.login(userToBeInserted)
                withContext(Dispatchers.Main) {
                    signupComplete.value = true
                }
            }

        }


    }

}