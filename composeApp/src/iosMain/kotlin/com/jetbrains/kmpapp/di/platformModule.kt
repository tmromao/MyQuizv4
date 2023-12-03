package com.jetbrains.kmpapp.di

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.jetbrains.kmpapp.data.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule() : Module = module {
    single { DatabaseDriverFactory() }
}