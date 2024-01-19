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


    /* Na pratica é preferivel designar por uiData porque é do que trata
    * interessante passar usecase
    * ver exemplo MovieApp no Github - Starts*/
    private val _state = MutableStateFlow<QuestionListState>(
        QuestionListState(
            /*questions = questions*/
        )
    )
    val uiState = _state.asStateFlow()

    init {
        screenModelScope.launch {

            initializeQuestions()
            getLocalQuestions()

            /*questionDataSource.getQuestions().collect {
                val questions = questionDataSource.getQuestions().first()
                _state.update {
                    it.copy(questions = questions)
                }
                *//*_state.value = _state.value.copy(
                    questions = it
                )*//*
            }*/

        }

    }

    private suspend fun initializeQuestions() {
        screenModelScope.launch {
            questionDataSource.insertQuestion(
                QuestionObject(
                    id = 1,
                    question = "Q1 - What is the capital of France?",
                    answer = "A1 - Paris"
                )
            )
            questionDataSource.insertQuestion(
                QuestionObject(
                    id = 2,
                    question = "Q2 - What is the capital of Italy?",
                    answer = "A2 - Rome"
                )
            )
            questionDataSource.insertQuestion(
                QuestionObject(
                    id = 3,
                    question = "Q3 - What is the capital of Spain?",
                    answer = "A3 - Madrid"
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


    private fun getLocalQuestions() {
        screenModelScope.launch {
            questionDataSource.getQuestions().collect { questions ->
                _state.update {
                    it.copy(questions = questions)
                }
                /*val questions = questionDataSource.getQuestions().first()
                _state.update {
                    it.copy(questions = questions)
                }*/
            }
        }
    }

    fun onEvent(event: QuestionListEvent) {
        when (event) {
            is QuestionListEvent.Load -> {
                getLocalQuestions()
            }
            //TODO: Implementar. Quando clickar na resposta, mostra a resposta a correta
            //ativa o botão de next
            is QuestionListEvent.OnQuestionClick -> {
                //navigator.pushScreen(QuestionScreen)
            }
            //TODO: Implementar. Quando clickar no botão de next, mostra a próxima pergunta
            is QuestionListEvent.OnNextClick -> {
                //navigator.pushScreen(QuestionScreen)
            }
        }

    }

    override fun onDispose() {
        super.onDispose()
        //questionDataSource.close()
    }
}

/*
private val questions = (1..20).map {
    QuestionObject(
        id = it.toLong(),
        question = "Question $it",
        answer = "Answer $it"
    )
}*/
