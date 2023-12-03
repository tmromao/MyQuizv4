package com.jetbrains.kmpapp.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jetbrains.kmpapp.data.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule() : Module = module {
    single { DatabaseDriverFactory(get()) }
}