package com.maksimowiczm.findmyip.infrastructure.notification

import com.maksimowiczm.findmyip.R
import com.maksimowiczm.findmyip.shared.core.application.infrastructure.notification.Notification
import com.maksimowiczm.findmyip.shared.core.application.infrastructure.notification.NotificationResources

internal class AndroidNotificationResources : NotificationResources {
    override fun getNotificationIcon(topic: Notification.Topic): Int =
        when (topic) {
            Notification.Topic.AddressChanged -> R.drawable.ic_notification_launcher
        }
}
