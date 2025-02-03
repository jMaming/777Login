package com.example.myapplication.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.LoadingDialog
import com.example.myapplication.ui.screen.home.HomeScreen
import com.example.myapplication.ui.state.LoginState
import com.example.myapplication.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val loginState by loginViewModel.loginState.collectAsState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            errorMessage = ""
            when (loginState) {
                is LoginState.Loading -> {
                    isLoading = true
                }

                is LoginState.Success -> {
                    isLoading = false
                    val successState = loginState as LoginState.Success
                    HomeScreen(successState.username)
                }

                is LoginState.Error -> {
                    isLoading = false
                    errorMessage = (loginState as LoginState.Error).message
                }

                else -> {
                    isLoading = false
                }
            }

            if (loginState is LoginState.Loading || loginState is LoginState.Error || loginState is LoginState.None) {
                // Username Field
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Error message
                Text(
                    text = errorMessage,
                    color = Color.Red
                )

                // Login Button
                Button(
                    onClick = { loginViewModel.login(username, password) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
            }
        }
        if (isLoading) {
            LoadingDialog {

            }
        }
    }
}