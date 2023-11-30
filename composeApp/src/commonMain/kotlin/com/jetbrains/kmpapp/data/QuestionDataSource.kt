package com.jetbrains.kmpapp.data

import kotlinx.coroutines.flow.Flow

interface QuestionDataSource {

    fun getQuestions(): Flow<List<QuestionObject>>

    fun getQuestionById(questionId: Long): Flow<QuestionObject?>

    suspend fun insertQuestion(question: QuestionObject)

    suspend fun deleteQuestion(id: Long)
}