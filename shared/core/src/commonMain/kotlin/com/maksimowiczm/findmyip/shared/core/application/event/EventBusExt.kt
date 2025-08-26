package com.maksimowiczm.findmyip.shared.core.application.event

import com.maksimowiczm.findmyip.shared.core.domain.event.DomainEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance

/** Subscribes to events of type [E] and returns a [Flow] of those events. */
inline fun <reified E : DomainEvent> EventBus.subscribe(): Flow<E> = events.filterIsInstance<E>()
