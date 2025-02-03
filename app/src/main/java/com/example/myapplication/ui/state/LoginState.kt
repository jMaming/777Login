package com.example.myapplication.ui.state

import com.example.myapplication.data.model.User

sealed class LoginState {
    data object Loading : LoginState()
    data object None : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()
}