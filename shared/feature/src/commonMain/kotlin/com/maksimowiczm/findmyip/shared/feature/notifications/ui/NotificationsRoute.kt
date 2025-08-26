package com.maksimowiczm.findmyip.shared.feature.notifications.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.maksimowiczm.findmyip.shared.feature.notifications.presentation.NotificationSettingsIntent
import com.maksimowiczm.findmyip.shared.feature.notifications.presentation.NotificationSettingsUiState

@Composable
fun NotificationsRoute(onBack: () -> Unit, modifier: Modifier = Modifier) {
    var uiState by remember {
        mutableStateOf<NotificationSettingsUiState>(NotificationSettingsUiState.Disabled)
    }

    NotificationScreen(
        onBack = onBack,
        uiState = uiState,
        onIntent = {
            when (it) {
                is NotificationSettingsIntent.ToggleNotifications -> {
                    uiState =
                        if (it.newState)
                            NotificationSettingsUiState.Enabled(
                                wifiEnabled = false,
                                cellularEnabled = false,
                                vpnEnabled = false,
                                ipv4Enabled = false,
                                ipv6Enabled = false,
                                unknownEnabled = false,
                            )
                        else NotificationSettingsUiState.Disabled
                }

                is NotificationSettingsIntent.ToggleCellular,
                is NotificationSettingsIntent.ToggleIpv4,
                is NotificationSettingsIntent.ToggleIpv6,
                is NotificationSettingsIntent.ToggleVpn,
                is NotificationSettingsIntent.ToggleWifi,
                is NotificationSettingsIntent.ToggleUnknown -> Unit
            }
        },
        modifier = modifier,
    )
}
