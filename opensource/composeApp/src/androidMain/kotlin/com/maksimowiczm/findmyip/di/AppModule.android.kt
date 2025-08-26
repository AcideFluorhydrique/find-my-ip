package com.maksimowiczm.findmyip.di

import androidx.room.Room
import com.maksimowiczm.findmyip.infrastructure.notification.AndroidNotificationResources
import com.maksimowiczm.findmyip.infrastructure.room.FindMyIpDatabase
import com.maksimowiczm.findmyip.infrastructure.room.FindMyIpDatabase.Companion.buildDatabase
import com.maksimowiczm.findmyip.shared.core.application.infrastructure.notification.NotificationResources
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.scope.Scope
import org.koin.dsl.bind

internal actual fun Scope.database(): FindMyIpDatabase =
    Room.databaseBuilder(
            context = get(),
            klass = FindMyIpDatabase::class.java,
            name = DATABASE_NAME,
        )
        .buildDatabase()

internal actual fun Module.platformModule() {
    factoryOf(::AndroidNotificationResources).bind<NotificationResources>()
}
