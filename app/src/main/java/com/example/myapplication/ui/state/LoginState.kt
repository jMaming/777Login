package com.example.myapplication.ui.state

sealed class LoginState {
    data object Loading : LoginState()
    data object None : LoginState()
    data class Success(val username: String) : LoginState()
    data class Error(val message: String) : LoginState()
}