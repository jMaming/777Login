package com.example.myapplication.data.remote

import kotlinx.coroutines.delay
import javax.inject.Inject


class ApiService @Inject constructor() {
    suspend fun login(username: String, password: String): Boolean {
        delay(2000)
        return username == "admin" && password == "password"
    }
}