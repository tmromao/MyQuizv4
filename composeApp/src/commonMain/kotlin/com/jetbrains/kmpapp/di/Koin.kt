package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.data.InMemoryMuseumStorage
import com.jetbrains.kmpapp.data.KtorMuseumApi
import com.jetbrains.kmpapp.data.MuseumApi
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.data.MuseumStorage
import com.jetbrains.kmpapp.data.SqlDelightQuestionDataSource
import com.jetbrains.kmpapp.screens.detail.DetailScreenModel
import com.jetbrains.kmpapp.screens.list.ListScreenModel
import com.jetbrains.kmpapp.screens.components.QuestionScreenModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            screenModelsModule,

            sqlDelightModule,
            platformModule(),

            dataModule,

            )
    }

val screenModelsModule = module {
    factory { ListScreenModel(museumRepository = get()) }
    factory { DetailScreenModel(museumRepository = get()) }
    factory { QuestionScreenModel(questionDataSource = get()) }
}

val sqlDelightModule = module {
    single { SqlDelightQuestionDataSource(get()) }
}


val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<MuseumApi> { KtorMuseumApi(get()) }
    single<MuseumStorage> { InMemoryMuseumStorage() }
    single {
        MuseumRepository(get(), get()).apply {
            initialize()
        }
    }

}

fun initKoin() = initKoin {}
