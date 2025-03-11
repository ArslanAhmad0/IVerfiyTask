package com.iverify.task.data.remote.device

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceResponseDto(
    @SerialName("devices") val devices: List<DeviceDto>
)