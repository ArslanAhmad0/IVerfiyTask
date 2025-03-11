package com.iverify.task.domain.usecase

import com.iverify.task.domain.model.Device
import com.iverify.task.domain.repository.DeviceRepository

class GetDevicesUseCase(private val repository: DeviceRepository) {
    suspend operator fun invoke(pageNumber: Int = 1, pageSize: Int = 100): List<Device> {
        return repository.getDevices(pageNumber, pageSize)
    }
}