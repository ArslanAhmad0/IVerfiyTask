package com.iverify.task.presentation.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iverify.task.domain.model.Device
import com.iverify.task.domain.usecase.GetDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(
    private val getDevicesUseCase: GetDevicesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DeviceListState>(DeviceListState.Loading)
    val uiState: StateFlow<DeviceListState> = _uiState

    init {
        fetchDevices()
    }

    private fun fetchDevices(pageNumber: Int = 1, pageSize: Int = 100) {
        viewModelScope.launch {
            try {
                val devices = getDevicesUseCase(pageNumber, pageSize)
                _uiState.value = DeviceListState.Success(devices)
            } catch (e: Exception) {
                _uiState.value = DeviceListState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun searchDevices(query: String) {
        val currentState = _uiState.value
        if (currentState is DeviceListState.Success) {
            val filtered = currentState.devices.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _uiState.value = DeviceListState.Success(filtered)
        }
    }
}


sealed class DeviceListState {
    object Loading : DeviceListState()
    data class Success(val devices: List<Device>) : DeviceListState()
    data class Error(val message: String) : DeviceListState()
}