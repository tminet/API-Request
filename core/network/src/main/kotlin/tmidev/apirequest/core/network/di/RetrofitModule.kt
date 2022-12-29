package tmidev.apirequest.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import tmidev.apirequest.core.network.retrofit.JsonPlaceholderApiRetrofit
import tmidev.apirequest.core.network.util.Constants.API_URL
import tmidev.apirequest.core.network.util.Constants.TIMEOUT_MILLISECONDS
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .connectTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Singleton
    fun providesJsonPlaceholderApiRetrofit(
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): JsonPlaceholderApiRetrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .baseUrl(API_URL)
        .build()
        .create()
}