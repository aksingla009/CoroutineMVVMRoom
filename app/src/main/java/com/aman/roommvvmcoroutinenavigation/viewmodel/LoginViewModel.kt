package com.aman.roommvvmcoroutinenavigation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aman.roommvvmcoroutinenavigation.model.LoginState
import com.aman.roommvvmcoroutinenavigation.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(username: String, password: String) {
        coroutineScope.launch {
            val userFromDB = db.getUser(username)
            if (userFromDB == null) {
                withContext(Dispatchers.Main) {
                    error.value = "User not Found"
                }
            } else {
                if (userFromDB.passwordHash == password.hashCode()) {
                    LoginState.login(userFromDB)
                    withContext(Dispatchers.Main) {
                        loginComplete.value = true
                    }
                }else{
                    withContext(Dispatchers.Main) {
                        error.value = "Password is incorrect"
                    }
                }

            }

        }


    }
}