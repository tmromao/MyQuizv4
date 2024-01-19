package com.jetbrains.kmpapp.data


import com.jetbrains.kmpapp.database.QuestionsDatabase


class SqlDelightQuestionDataSource(
    private val driverProvider : DatabaseDriverFactory,
)  {

    private var database:QuestionsDatabase? = null

    private suspend fun initDatabase(){
        if(database == null) {
            database = QuestionsDatabase.invoke(
                driver = driverProvider.createDriver()
            )
        }
    }
    suspend operator fun <R> invoke(block: suspend (QuestionsDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }

}