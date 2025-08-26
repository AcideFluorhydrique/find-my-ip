package com.maksimowiczm.findmyip.shared.feature.notifications.di

import com.maksimowiczm.findmyip.shared.feature.notifications.presentation.NotificationSettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedFeatureNotificationsModule = module { viewModelOf(::NotificationSettingsViewModel) }
