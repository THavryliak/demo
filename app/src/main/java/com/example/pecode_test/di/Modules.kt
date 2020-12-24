package com.example.pecode_test.di

import com.example.pecode_test.notifications.NotificationHandler
import org.koin.core.module.Module
import org.koin.dsl.module

val AppModules: Module = module {
    single { NotificationHandler() }
}