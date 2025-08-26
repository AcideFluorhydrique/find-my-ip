package com.maksimowiczm.findmyip.shared.core.domain.event

import com.maksimowiczm.findmyip.shared.core.domain.AddressHistory

data class IpAddressChangedEvent(val newAddress: AddressHistory) : DomainEvent
