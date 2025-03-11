package com.iverify.task.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iverify.task.data.util.tokenmanager.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val _signInState = MutableStateFlow(SignInState())
    val signInState: StateFlow<SignInState> get() = _signInState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _signInState.value = SignInState(isLoading = true)
            try {
                delay(2000L)
                // Considering this token is returned from Signing API
                val token = "PlRDI6heokZGZznI7AwG55a3rq9h9fmL"
                tokenManager.saveToken(token)
                _signInState.value = SignInState(isSuccessful = true)
            } catch (e: Exception) {
                _signInState.value = SignInState(errorMessage = e.message)
            }
        }
    }
}

data class SignInState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorMessage: String? = null
)