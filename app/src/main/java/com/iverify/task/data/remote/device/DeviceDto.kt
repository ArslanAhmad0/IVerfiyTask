package com.iverify.task.data.remote.device

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceDto(
    @SerialName("accessCode") val accessCode: String,
    @SerialName("name") val name: String,
    @SerialName("lastScanDate") val lastScanDate: String
)