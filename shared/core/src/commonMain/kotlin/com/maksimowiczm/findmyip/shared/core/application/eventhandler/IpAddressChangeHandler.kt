package com.maksimowiczm.findmyip.shared.core.application.eventhandler

import com.maksimowiczm.findmyip.shared.core.application.event.EventHandler
import com.maksimowiczm.findmyip.shared.core.application.infrastructure.notification.Notification
import com.maksimowiczm.findmyip.shared.core.application.infrastructure.notification.NotificationService
import com.maksimowiczm.findmyip.shared.core.domain.NetworkType
import com.maksimowiczm.findmyip.shared.core.domain.event.IpAddressChangedEvent
import findmyip.composeapp.generated.resources.*
import org.jetbrains.compose.resources.getString

internal class IpAddressChangeHandler(private val notificationService: NotificationService) :
    EventHandler<IpAddressChangedEvent> {
    override suspend fun handle(event: IpAddressChangedEvent) {
        val notification =
            Notification.Info(
                id = event.newAddress.id.toInt(),
                topic = Notification.Topic.AddressChanged,
                title = getString(Res.string.headline_new_address_detected),
                message =
                    buildString {
                        append(event.newAddress.stringRepresentation())
                        append(", ")
                        append(event.newAddress.networkType.getString())
                    },
            )

        notificationService.notify(notification)
    }

    private suspend fun NetworkType.getString(): String =
        when (this) {
            NetworkType.Cellular -> getString(Res.string.cellular)
            NetworkType.Unknown -> getString(Res.string.unknown)
            NetworkType.VPN -> getString(Res.string.vpn)
            NetworkType.WiFi -> getString(Res.string.wifi)
        }
}
