package com.example.bookshelf.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BookShelfApiService {
    @GET("volumes?")
    suspend fun getBooks(@Query("q") searchTerms: String): SearchResult

    @GET("volumes/{bookId}?fields=volumeInfo(imageLinks/medium)")
    suspend fun getBookById(@Path("bookId") bookId: String): Item
}
