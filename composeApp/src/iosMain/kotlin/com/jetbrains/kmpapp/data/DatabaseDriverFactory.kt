package com.jetbrains.kmpapp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.jetbrains.kmpapp.database.QuestionsDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver{
        return NativeSqliteDriver(QuestionsDatabase.Schema, "questions.db")
    }
}