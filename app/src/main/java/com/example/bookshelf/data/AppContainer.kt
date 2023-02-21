package com.example.bookshelf.data

import com.example.bookshelf.network.BookShelfApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val bookShelfRepository: BookShelfRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://www.googleapis.com/books/v1/"

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: BookShelfApiService by lazy {
        retrofit.create(BookShelfApiService::class.java)
    }
    override val bookShelfRepository: BookShelfRepository by lazy {
        DefaultBookRepository(retrofitService)
    }


}