package com.adhitya.katalogfilm.di

import com.adhitya.katalogfilm.BuildConfig
import com.adhitya.katalogfilm.data.source.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val BASE_URL="https://api.themoviedb.org/3/"
        @Singleton
        @Provides
        fun provideHttpClient() : OkHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

        @Singleton
        @Provides
        fun provideRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        @Provides
        fun provideFilmApi(retrofit: Retrofit) : ApiService = retrofit.create(ApiService::class.java)

    }
}