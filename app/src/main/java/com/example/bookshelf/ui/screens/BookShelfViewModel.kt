package com.example.bookshelf.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BookShelfRepository
import com.example.bookshelf.fakeUrls
import com.example.bookshelf.network.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookShelfViewModel(
    private val bookShelfRepository: BookShelfRepository
) : ViewModel() {
    var searchText by mutableStateOf("")
        private set
    var bookShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Start)
        private set

    fun updateSearchText(searched: String) {
        searchText = searched
    }

    fun getBooks(searchTerms: String) {

        bookShelfUiState = BookShelfUiState.Loading

        viewModelScope.launch {
            val ids = withContext(Dispatchers.Default) {
                val volumeResult = bookShelfRepository.getBooks(searchTerms)
                val listOfIds = mutableListOf<String>()
                for (item in volumeResult.items) {
                    item.id?.let {
                        listOfIds.add(it)
                    }
                }
                return@withContext listOfIds
            }

            val urls = withContext(Dispatchers.Default) {
                val listOfUrls = mutableListOf<String?>()
                for (id in ids) {
                    val bookItem = bookShelfRepository.getBookById(id)
                    val url = bookItem.volumeInfo?.imageLinks?.medium?.replace("http", "https")
                    listOfUrls.add(url)
                }
                return@withContext listOfUrls
            }

            bookShelfUiState = BookShelfUiState.Success(urls)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookShelfApplication)
                val bookShelfRepository = application.container.bookShelfRepository
                BookShelfViewModel(bookShelfRepository = bookShelfRepository)
            }
        }
    }

    sealed interface BookShelfUiState {
        data class Success(val booksUrl: List<String?>) : BookShelfUiState
        object Start: BookShelfUiState
        object Error : BookShelfUiState
        object Loading : BookShelfUiState
    }
}


