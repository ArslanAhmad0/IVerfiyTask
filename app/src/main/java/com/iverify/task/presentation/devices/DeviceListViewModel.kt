package com.iverify.task.presentation.devices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iverify.task.domain.model.Device
import com.iverify.task.domain.usecase.GetDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class DeviceListViewModel @Inject constructor(
    private val getDevicesUseCase: GetDevicesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DeviceListState>(DeviceListState.Loading)
    val uiState: StateFlow<DeviceListState> = _uiState
    private var allDevices: List<Device> = emptyList()

    init {
        fetchDevices()
    }

    private fun fetchDevices(pageNumber: Int = 1, pageSize: Int = 100) {
        viewModelScope.launch {
            try {
                allDevices = getDevicesUseCase(pageNumber, pageSize)
                _uiState.value = DeviceListState.Success(allDevices)
            } catch (e: SerializationException) {
                _uiState.value = DeviceListState.Error("It's not you! something's wrong with our server.")
            } catch (e: UnknownHostException) {
                _uiState.value = DeviceListState.Error("Please check your internet connection")
            } catch (e: Exception) {
                _uiState.value = DeviceListState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    fun searchDevices(query: String) {
        val currentState = _uiState.value
        if (currentState is DeviceListState.Success) {
            val filtered = allDevices.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _uiState.value = DeviceListState.Success(filtered)
        }
    }
}


sealed class DeviceListState {
    data object Loading : DeviceListState()
    data class Success(val devices: List<Device>) : DeviceListState()
    data class Error(val message: String) : DeviceListState()
}