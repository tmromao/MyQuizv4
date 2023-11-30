package com.jetbrains.kmpapp.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.jetbrains.kmpapp.database.QuestionsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightQuestionDataSource(
    db: QuestionsDatabase,
) : QuestionDataSource {

    private val questionQueries = db.questionsQueries
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

}