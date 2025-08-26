package com.maksimowiczm.findmyip.shared.core.application.di

import com.maksimowiczm.findmyip.shared.core.application.event.EventBus
import com.maksimowiczm.findmyip.shared.core.application.event.SharedFlowEventBus
import com.maksimowiczm.findmyip.shared.core.application.eventhandler.IpAddressChangeHandler
import com.maksimowiczm.findmyip.shared.core.application.usecase.ObserveAddressHistoryUseCase
import com.maksimowiczm.findmyip.shared.core.application.usecase.ObserveAddressHistoryUseCaseImpl
import com.maksimowiczm.findmyip.shared.core.application.usecase.ObserveCurrentIpAddressUseCase
import com.maksimowiczm.findmyip.shared.core.application.usecase.ObserveCurrentIpAddressUseCaseImpl
import com.maksimowiczm.findmyip.shared.core.application.usecase.ObserveNotificationPreferencesUseCase
import com.maksimowiczm.findmyip.shared.core.application.usecase.ObserveNotificationPreferencesUseCaseImpl
import com.maksimowiczm.findmyip.shared.core.application.usecase.PartialUpdateNotificationPreferencesUseCase
import com.maksimowiczm.findmyip.shared.core.application.usecase.PartialUpdateNotificationPreferencesUseCaseImpl
import com.maksimowiczm.findmyip.shared.core.application.usecase.RefreshAddressUseCase
import com.maksimowiczm.findmyip.shared.core.application.usecase.RefreshAddressUseCaseImpl
import com.maksimowiczm.findmyip.shared.core.application.usecase.SaveAddressHistoryUseCase
import com.maksimowiczm.findmyip.shared.core.application.usecase.SaveAddressHistoryUseCaseImpl
import com.maksimowiczm.findmyip.shared.core.domain.InternetProtocolVersion
import com.maksimowiczm.findmyip.shared.core.domain.Ip4Address
import com.maksimowiczm.findmyip.shared.core.domain.NotificationPreferences
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module

private const val APPLICATION_COROUTINE_SCOPE = "APPLICATION_COROUTINE_SCOPE"

fun Scope.applicationCoroutineScope(): CoroutineScope = get(named(APPLICATION_COROUTINE_SCOPE))

fun sharedCoreApplicationModule(applicationCoroutineScope: CoroutineScope) = module {
    single(named(APPLICATION_COROUTINE_SCOPE)) { applicationCoroutineScope }

    refreshAddressUseCase(InternetProtocolVersion.IPV4)
    refreshAddressUseCase(InternetProtocolVersion.IPV6)

    factory {
            ObserveAddressHistoryUseCaseImpl(
                get(),
                get(named(InternetProtocolVersion.IPV4)),
                get(named(InternetProtocolVersion.IPV6)),
            )
        }
        .bind<ObserveAddressHistoryUseCase>()

    observeCurrentIpAddressUseCase(InternetProtocolVersion.IPV4)
    observeCurrentIpAddressUseCase(InternetProtocolVersion.IPV6)

    factoryOf(::SaveAddressHistoryUseCaseImpl).bind<SaveAddressHistoryUseCase>()

    singleOf(::SharedFlowEventBus).bind<EventBus>()

    eventHandler {
        IpAddressChangeHandler(
            get(),
            get(named(NotificationPreferences::class.qualifiedName.toString())),
            get(),
        )
    }

    factory {
            ObserveNotificationPreferencesUseCaseImpl(
                get(named(NotificationPreferences::class.qualifiedName.toString()))
            )
        }
        .bind<ObserveNotificationPreferencesUseCase>()
    factory {
            PartialUpdateNotificationPreferencesUseCaseImpl(
                get(named(NotificationPreferences::class.qualifiedName.toString()))
            )
        }
        .bind<PartialUpdateNotificationPreferencesUseCase>()
}

private fun Module.refreshAddressUseCase(protocolVersion: InternetProtocolVersion) {
    factory(named(protocolVersion)) {
            RefreshAddressUseCaseImpl<Ip4Address>(
                get(),
                get(named(protocolVersion)),
                get(named(protocolVersion)),
                get(),
                get(),
                get(),
                get(),
            )
        }
        .bind<RefreshAddressUseCase>()
}

private fun Module.observeCurrentIpAddressUseCase(protocolVersion: InternetProtocolVersion) {
    factory(named(protocolVersion)) {
            ObserveCurrentIpAddressUseCaseImpl<Ip4Address>(get(named(protocolVersion)))
        }
        .bind<ObserveCurrentIpAddressUseCase<Ip4Address>>()
}
