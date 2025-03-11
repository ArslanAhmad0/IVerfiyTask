package com.iverify.task.data.repository

import com.iverify.task.data.remote.device.DeviceApi
import com.iverify.task.data.remote.device.toDomain
import com.iverify.task.domain.model.Device
import com.iverify.task.domain.repository.DeviceRepository
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val api: DeviceApi
) : DeviceRepository {

    override suspend fun getDevices(pageNumber: Int, pageSize: Int): List<Device> {
        if (pageSize > 1000) throw RuntimeException("Max page size allowed is 1000")
        val response = api.getDevices(pageNumber, pageSize)
        return response.map { it.toDomain() }
    }
}