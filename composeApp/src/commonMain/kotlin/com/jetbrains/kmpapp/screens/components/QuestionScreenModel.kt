package com.jetbrains.kmpapp.screens.components

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.QuestionListEvent
import com.jetbrains.kmpapp.data.QuestionListState
import com.jetbrains.kmpapp.data.QuestionObject
import com.jetbrains.kmpapp.data.SqlDelightQuestionDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class QuestionScreenModel(
    private val questionDataSource: SqlDelightQuestionDataSource,
) : ScreenModel {

    //private val _state = MutableStateFlow(QuestionListState())
    private val _state = MutableStateFlow(QuestionListState())
    val state = combine(
        _state,
        questionDataSource.getQuestions(),
    ) { state, questions ->
        state.copy(
            questions = questions
        )
    }.stateIn(
        scope = screenModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = QuestionListState()
    )

    init {


    }


    //fun getObject() : Flow<QuestionObject?> = _state.asStateFlow()

    fun getObject(): QuestionObject = QuestionObject(
        id = 1,
        question = "Question 1",
        answer = "Answer 1"
    )

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