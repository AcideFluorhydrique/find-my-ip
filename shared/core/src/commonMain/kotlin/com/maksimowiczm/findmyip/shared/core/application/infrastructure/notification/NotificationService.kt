package com.maksimowiczm.findmyip.shared.core.application.infrastructure.notification

interface NotificationService {
    suspend fun notify(notification: Notification)
}
