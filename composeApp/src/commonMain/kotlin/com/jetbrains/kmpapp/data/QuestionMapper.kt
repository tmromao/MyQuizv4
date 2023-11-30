package com.jetbrains.kmpapp.data

import database.QuestionEntity

fun QuestionEntity.toQuestionObject(): QuestionObject {
    return QuestionObject(
        id = id,
        question = question,
        answer = answer
    )
}