package com.jetbrains.kmpapp.screens.components

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
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

   /* init {
        screenModelScope.launch {

            //TODO: this is not working
            uiState.value.copy(
                questions = questionDataSource.getQuestions().first()
            )
            *//*state = combine(
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
            ) as MutableStateFlow<QuestionListState>*//*
        }

    }*/

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