package com.jetbrains.kmpapp.screens.components

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow


data class QuestionScreen(val objectId: Int) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel: QuestionScreenModel = getScreenModel()

        val obj = screenModel.getObject()
    }


}
