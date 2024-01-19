package com.jetbrains.kmpapp.data

import app.cash.sqldelight.coroutines.asFlow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CacheDataImp(
    private val sharedDatabase: SqlDelightQuestionDataSource,
) : QuestionDataSource {


    override suspend fun getQuestions(): Flow<List<QuestionObject>> =
        sharedDatabase { appDatabase ->
            appDatabase.questionsQueries.getQuestions(::mapQuestion).asFlow()
                .map { it.executeAsList() }
        }

    override suspend fun getQuestionById(questionId: Long): Flow<QuestionObject?> =
        sharedDatabase { appDatabase ->
            appDatabase.questionsQueries.getQuestionEntity(questionId, ::mapQuestion).asFlow()
                .map { it.executeAsOneOrNull() }
        }

    override suspend fun insertQuestion(question: QuestionObject) {
        sharedDatabase { appDatabase ->
            appDatabase.questionsQueries.insertQuestionEntity(
                id = question.id,
                question = question.question,
                answer = question.answer
            )
        }


    }

    override suspend fun deleteQuestion(id: Long) {
        sharedDatabase { appDatabase ->
            appDatabase.questionsQueries.deleteQuestionEntity(id)
        }

        /*questionQueries.deleteQuestionEntity(id)*/
    }

    private fun mapQuestion(
        id: Long,
        question: String,
        answer: String,
    ): QuestionObject = QuestionObject(id, question, answer)
}