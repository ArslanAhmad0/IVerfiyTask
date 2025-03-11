package com.iverify.task.data.remote.device

import com.iverify.task.domain.model.Device


fun DeviceDto.toDomain() = Device(
    name = name,
    lastScanDate = lastScanDate,
    accessCode = accessCode,
)