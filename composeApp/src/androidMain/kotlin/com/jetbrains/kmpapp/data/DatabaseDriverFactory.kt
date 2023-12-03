package com.jetbrains.kmpapp.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jetbrains.kmpapp.database.QuestionsDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual suspend fun createDriver(): SqlDriver{
        return AndroidSqliteDriver(QuestionsDatabase.Schema, context, "questions.db")
    }
}