package com.jetbrains.kmpapp

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jetbrains.kmpapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.dsl.module

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidLogger(if(isDebug()) Level.ERROR else Level.NONE)
            androidContext(this@MuseumApp)
        }
    }
}

fun Context.isDebug() = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE



