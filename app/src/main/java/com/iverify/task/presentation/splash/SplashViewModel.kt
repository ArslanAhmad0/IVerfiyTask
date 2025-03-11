package com.iverify.task.presentation.splash

import androidx.lifecycle.ViewModel
import com.iverify.task.data.util.tokenmanager.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenProvider: TokenManager
) : ViewModel() {
    val isSignedIn: Boolean
        get() = !tokenProvider.getToken().isNullOrEmpty()
}