package tmidev.apirequest.di

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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tmidev.apirequest.data.remote.ktor.JsonPlaceholderApiKtor
import tmidev.apirequest.data.remote.retrofit.JsonPlaceholderApiRetrofit
import tmidev.apirequest.util.API_URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FrameworkModule {
    private const val TIMEOUT_MILLISECONDS = 10000L

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .connectTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Singleton
    fun provideJsonPlaceholderApiRetrofit(
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): JsonPlaceholderApiRetrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .baseUrl(API_URL)
        .build()
        .create(JsonPlaceholderApiRetrofit::class.java)

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(engineFactory = Android) {
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
    fun provideJsonPlaceholderApiKtor(
        httpClient: HttpClient
    ): JsonPlaceholderApiKtor = JsonPlaceholderApiKtor(httpClient = httpClient)
}