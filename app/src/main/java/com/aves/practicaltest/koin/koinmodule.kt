package com.aves.practicaltest.koin

import com.aves.practicaltest.BuildConfig
import com.aves.practicaltest.repository.FirstRepository
import com.aves.practicaltest.retrofit.ApiInterface
import com.aves.practicaltest.viewmodel.FirstViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideForecastApi(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideForecastApi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

val myModule = module {
    single { FirstRepository(get()) }
    viewModel { FirstViewModel(get()) }
}