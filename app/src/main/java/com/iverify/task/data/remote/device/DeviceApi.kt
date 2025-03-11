package com.iverify.task.data.remote.device

import retrofit2.http.POST
import retrofit2.http.Query

interface DeviceApi {
    @POST("device")
    suspend fun getDevices(
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int
    ): List<DeviceDto>
}