package com.iverify.task.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.iverify.task.presentation.common.navigation.AppNav
import com.iverify.task.presentation.devices.DeviceListScreen
import com.iverify.task.presentation.devices.DeviceListViewModel
import com.iverify.task.presentation.common.theme.IVerifyTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IVerifyTaskTheme {
                AppNav()
            }
        }
    }
}