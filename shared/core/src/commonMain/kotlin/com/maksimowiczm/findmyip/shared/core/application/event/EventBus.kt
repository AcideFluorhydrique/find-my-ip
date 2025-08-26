package com.maksimowiczm.findmyip.shared.core.application.event

import com.maksimowiczm.findmyip.shared.core.domain.event.DomainEvent
import kotlinx.coroutines.flow.Flow

interface EventBus {
    val events: Flow<DomainEvent>

    fun publish(event: DomainEvent)
}
