package com.iverify.task.data.util.tokenmanager

interface TokenManager {
    fun saveToken(token: String)
    fun getToken(): String?
}