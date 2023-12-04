package com.jetbrains.kmpapp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.jetbrains.kmpapp.database.QuestionsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightQuestionDataSource(
    //db: QuestionsDatabase,
    private val driverProvider : DatabaseDriverFactory,
) : QuestionDataSource {

    private var database:QuestionsDatabase? = null

    private val questionQueries = database!!.questionsQueries

    init {
        questionQueries.transaction {
            //questionQueries.deleteAll()
            questionQueries.insertQuestionEntity(
                id = 1,
                question = "Qual a capital de Portugal?",
                answer = "Lisboa"
            )
            questionQueries.insertQuestionEntity(
                id = 2,
                question = "Qual a capital da Espanha?",
                answer = "Madrid"
            )
            questionQueries.insertQuestionEntity(
                id = 3,
                question = "Qual a capital da França?",
                answer = "Paris"
            )
        }
    }
    override fun getQuestions(): Flow<List<QuestionObject>> {
        return questionQueries
            .getQuestions()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { questionEntities ->
                questionEntities.map { questionEntity ->
                    questionEntity.toQuestionObject()
                }
            }
    }

    override fun getQuestionById(questionId: Long): Flow<QuestionObject?> {
        return questionQueries
            .getQuestionEntity(questionId.toLong())
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { questionEntities ->
                questionEntities.map { questionEntity ->
                    questionEntity.toQuestionObject()
                }.firstOrNull()
            }
    }

    override suspend fun insertQuestion(question: QuestionObject) {
        questionQueries.insertQuestionEntity(
            id = question.id,
            question = question.question,
            answer = question.answer
        )
    }

    override suspend fun deleteQuestion(id: Long) {
        questionQueries.deleteQuestionEntity(id)
    }

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