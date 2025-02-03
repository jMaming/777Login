package com.example.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.NetworkRepository
import com.example.myapplication.data.model.User
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.ui.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService,
    private val networkRepository: NetworkRepository,
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.None)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            val isSuccess = apiService.login(username, password)

            if (isSuccess) {
                _loginState.value = LoginState.Success(User(username, password))
            } else {
                _loginState.value = LoginState.Error("Invalid username or password")
            }
        }
    }

    fun hasInternetConnection(context: Context) =
        networkRepository.isInternetAvailable(context)
}