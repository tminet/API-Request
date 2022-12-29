package tmidev.apirequest.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import tmidev.apirequest.core.network.ktor.JsonPlaceholderApiKtor
import tmidev.apirequest.core.network.util.Constants.TIMEOUT_MILLISECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {
    @Provides
    @Singleton
    fun providesHttpClient(): HttpClient = HttpClient(engineFactory = Android) {
        install(plugin = ContentNegotiation) {
            json(json = Json {
                ignoreUnknownKeys = true
            })
        }

        install(plugin = HttpTimeout) {
            requestTimeoutMillis = TIMEOUT_MILLISECONDS
            connectTimeoutMillis = TIMEOUT_MILLISECONDS
            socketTimeoutMillis = TIMEOUT_MILLISECONDS
        }
    }

    @Provides
    @Singleton
    fun providesJsonPlaceholderApiKtor(
        httpClient: HttpClient
    ): JsonPlaceholderApiKtor = JsonPlaceholderApiKtor(httpClient = httpClient)
}