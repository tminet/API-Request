package tmidev.apirequest.core.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitImplementation

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class KtorImplementation