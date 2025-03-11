package com.iverify.task.presentation.devices

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iverify.task.domain.model.Device

@Composable
fun DeviceListScreen(viewModel: DeviceListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query ->
                    searchQuery = query
                    viewModel.searchDevices(query)
                },
                maxLines = 1,
                label = { Text("Search Devices") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            when (uiState) {
                is DeviceListState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is DeviceListState.Success -> {
                    val devices = (uiState as DeviceListState.Success).devices
                    LazyColumn {
                        items(devices) { device ->
                            DeviceItem(device)
                        }
                    }
                }

                is DeviceListState.Error -> {
                    Text(
                        text = "Error: ${(uiState as DeviceListState.Error).message}",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun DeviceItem(device: Device) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "User: ${device.name}", style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Last Scan: ${device.lastScanDate}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Access Code: ${device.accessCode}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}