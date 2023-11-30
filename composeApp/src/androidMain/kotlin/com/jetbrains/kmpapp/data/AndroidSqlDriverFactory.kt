package com.jetbrains.kmpapp.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jetbrains.kmpapp.database.QuestionsDatabase
import org.koin.core.scope.Scope
import org.koin.android.ext.koin.androidContext

actual fun Scope.sqlDriverFactory(): SqlDriver {

    return AndroidSqliteDriver(QuestionsDatabase.Schema, androidContext(), "questions.db")
}