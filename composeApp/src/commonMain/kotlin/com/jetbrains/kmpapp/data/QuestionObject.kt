package com.jetbrains.kmpapp.data

data class QuestionObject(
    val id: Long?,
    val question: String,
    val answer: String,
)

data class QuestionListState(
    val questions: List<QuestionObject> = emptyList(),
)

sealed interface QuestionListEvent{
    object Load : QuestionListEvent

    data class OnQuestionClick(val question: QuestionObject) : QuestionListEvent

    object OnNextClick : QuestionListEvent

}