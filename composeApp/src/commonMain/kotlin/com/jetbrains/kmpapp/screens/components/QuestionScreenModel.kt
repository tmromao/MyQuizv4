package com.jetbrains.kmpapp.screens.components

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.QuestionListEvent
import com.jetbrains.kmpapp.data.QuestionListState
import com.jetbrains.kmpapp.data.QuestionObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class QuestionScreenModel(): ScreenModel {

    private val _state = MutableStateFlow(QuestionListState(
        questions = questions
    ))
    val state = _state.asStateFlow()

    //fun getObject() : Flow<QuestionObject?> = _state.asStateFlow()

    fun getObject() : QuestionObject = QuestionObject(
        id = 1,
        question = "Question 1",
        answer = "Answer 1"
    )
    fun onEvent(event: QuestionListEvent){

    }

}

private val questions = (1..20).map {
    QuestionObject(
        id = it.toLong(),
        question = "Question $it",
        answer = "Answer $it"
    )
}