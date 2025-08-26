package com.maksimowiczm.findmyip.screenshot

import androidx.compose.runtime.Composable
import com.maksimowiczm.findmyip.shared.feature.notifications.ui.NotificationScreenPreview

data object NotificationScreenScreenshot : Screenshot {
    override val name: String = "3"

    @Composable override fun Content() = NotificationScreenPreview()
}
