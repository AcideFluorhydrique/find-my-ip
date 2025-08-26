package com.maksimowiczm.findmyip.shared.feature.notifications.ui

import androidx.compose.runtime.Composable
import com.maksimowiczm.findmyip.shared.core.feature.ui.FindMyIpTheme
import com.maksimowiczm.findmyip.shared.feature.notifications.presentation.NotificationSettingsUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun NotificationScreenPreview() {
    FindMyIpTheme {
        NotificationScreenImpl(
            onBack = {},
            uiState =
                NotificationSettingsUiState.Enabled(
                    wifiEnabled = true,
                    cellularEnabled = false,
                    vpnEnabled = false,
                    unknownEnabled = false,
                    ipv4Enabled = true,
                    ipv6Enabled = true,
                ),
            onIntent = {},
            onSystemSettings = null,
        )
    }
}
