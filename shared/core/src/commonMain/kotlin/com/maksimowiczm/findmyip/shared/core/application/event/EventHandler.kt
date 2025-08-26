package com.maksimowiczm.findmyip.shared.core.application.event

import com.maksimowiczm.findmyip.shared.core.domain.event.DomainEvent

fun interface EventHandler<E : DomainEvent> {
    suspend fun handle(event: E)
}
