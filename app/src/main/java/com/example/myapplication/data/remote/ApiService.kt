package com.example.myapplication.data.remote

import kotlinx.coroutines.delay


class ApiService {
    suspend fun login(username: String, password: String): Boolean {
        delay(2000)
        return username == "admin" && password == "password"
    }
}