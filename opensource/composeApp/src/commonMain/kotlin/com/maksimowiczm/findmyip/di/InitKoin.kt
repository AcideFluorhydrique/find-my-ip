package com.maksimowiczm.findmyip.di

import com.maksimowiczm.findmyip.shared.core.application.di.sharedCoreApplicationModule
import com.maksimowiczm.findmyip.shared.core.infrastructure.di.sharedCoreInfrastructureModule
import com.maksimowiczm.findmyip.shared.feature.home.di.sharedFeatureHomeModule
import com.maksimowiczm.findmyip.shared.feature.notifications.di.sharedFeatureNotificationsModule
import kotlinx.coroutines.CoroutineScope
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Initializes Koin with the provided configuration and modules.
 *
 * @param applicationCoroutineScope CoroutineScope with whole application lifecycle.
 * @param config Optional KoinAppDeclaration to configure Koin.
 */
internal fun initKoin(
    applicationCoroutineScope: CoroutineScope,
    config: KoinAppDeclaration? = null,
) = startKoin {
    config?.invoke(this)

    modules(
        appModule,
        sharedCoreApplicationModule(applicationCoroutineScope),
        sharedCoreInfrastructureModule,
        sharedFeatureHomeModule,
        sharedFeatureNotificationsModule,
    )
}
