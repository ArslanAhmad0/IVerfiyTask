package com.iverify.task.domain.repository

import com.iverify.task.domain.model.Device

interface DeviceRepository {
    suspend fun getDevices(pageNumber: Int, pageSize: Int): List<Device>
}