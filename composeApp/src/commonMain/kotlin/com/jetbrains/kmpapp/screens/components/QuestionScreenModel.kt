package com.jetbrains.kmpapp.screens.components

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.data.CacheDataImp
import com.jetbrains.kmpapp.data.QuestionListEvent
import com.jetbrains.kmpapp.data.QuestionListState
import com.jetbrains.kmpapp.data.QuestionObject
import kotlinx.coroutines.flow.*

import kotlinx.coroutines.launch

class QuestionScreenModel(
    private val questionDataSource: CacheDataImp,
) : ScreenModel {


    private val _state = MutableStateFlow(QuestionListState(
        questions = questions
    ))
    val uiState = _state.asStateFlow()
    init {
        screenModelScope.launch {

            questionDataSource.insertQuestion(
                QuestionObject(
                    id = 1,
                    question = "Question 1",
                    answer = "Answer 1"
                )
            )
        }

    }

    //fun getObject() : Flow<QuestionObject?> = _state.asStateFlow()
    fun getObject(): QuestionObject = QuestionObject(
        id = 1,
        question = "Question 1",
        answer = "Answer 1"
    )

    fun getQuestions() {
        screenModelScope.launch {
            _state.value = _state.value.copy(
                questions = questionDataSource
                    .getQuestions().first()
            )
        }
    }

    fun onEvent(event: QuestionListEvent) {

    }

    override fun onDispose() {
        super.onDispose()
        //questionDataSource.close()
    }
}

private val questions = (1..20).map {
    QuestionObject(
        id = it.toLong(),
        question = "Question $it",
        answer = "Answer $it"
    )
}