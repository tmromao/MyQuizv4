package com.jetbrains.kmpapp.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jetbrains.kmpapp.data.QuestionListEvent
import com.jetbrains.kmpapp.data.QuestionListState
import com.jetbrains.kmpapp.data.QuestionObject
import com.jetbrains.kmpapp.screens.list.ListScreenModel
import org.koin.core.component.getScopeId


const val KEY_QUESTION_SCREEN = "question"

data object QuestionScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel: QuestionScreenModel = getScreenModel()

        val state by screenModel.uiState.collectAsState()

        //TODO: This is not working - need to figure out why
        /* aqui devia iniciar o load das questão inicial, id=1
        LaunchedEffect(Unit) {
            /* Esta linha nao sera necessaria */
            screenModel.onEvent(QuestionListEvent.Load)
            /* Aqui printa a lista de questões */
            println(state.questions)
        }*/

        ObjectLazyColumn(
            objects = state.questions,
            onEvent = {}
        )
    }
}

@Composable
private fun ObjectLazyColumn(
    objects: List<QuestionObject>,
    onEvent: (QuestionListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn()
    {
        items(objects, key = { (it.id)!!.toInt() }) { questionObject ->
            QuestionItem(
                questionObject = questionObject,
                modifier = Modifier.padding(16.dp)
            )
        }
    }


}

@Composable
fun QuestionItem(
    questionObject: QuestionObject,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Text("Pergunta: ${questionObject.question}")

        Spacer(Modifier.width(16.dp))

        Text("Resposta: ${questionObject.answer}")
    }

}
