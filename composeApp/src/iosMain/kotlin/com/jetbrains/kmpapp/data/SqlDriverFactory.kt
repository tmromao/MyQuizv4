package com.jetbrains.kmpapp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.jetbrains.kmpapp.database.QuestionsDatabase
import org.koin.core.scope.Scope

actual fun Scope.sqlDriverFactory(): SqlDriver {

    return NativeSqliteDriver(QuestionsDatabase.Schema, "questions.db")

}