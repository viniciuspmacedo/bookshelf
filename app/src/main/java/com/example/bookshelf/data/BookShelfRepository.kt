package com.example.bookshelf.data


import com.example.bookshelf.network.BookShelfApiService
import com.example.bookshelf.network.Item
import com.example.bookshelf.network.SearchResult

interface BookShelfRepository {
    suspend fun getBooks(searchTerms: String): SearchResult
    suspend fun getBookById(bookId: String): Item
}

class DefaultBookRepository(private val bookShelfApiService: BookShelfApiService) :
    BookShelfRepository {
    override suspend fun getBooks(searchTerms: String): SearchResult {
        return bookShelfApiService.getBooks(searchTerms)
    }

    override suspend fun getBookById(bookId: String): Item {
        return bookShelfApiService.getBookById(bookId)
    }

}