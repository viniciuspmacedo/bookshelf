package com.example.bookshelf.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookshelf.fakeUrls
import com.example.bookshelf.ui.screens.BookShelfViewModel
import com.example.bookshelf.ui.screens.HomeScreen
import com.example.bookshelf.ui.screens.SearchTopAppBar
import com.example.bookshelf.ui.theme.BookShelfTheme

@Composable
fun BookShelfApp(
    bookShelfViewModel: BookShelfViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier.padding(horizontal = 8.dp),
        topBar = {
            SearchTopAppBar(
                searchText = bookShelfViewModel.searchText,
                onSearchChange = { bookShelfViewModel.updateSearchText(it) },
                onKeyBoardSearch = { bookShelfViewModel.getBooks(bookShelfViewModel.searchText) }
            )
        }) {
        Surface(modifier = Modifier.padding(it)) {
            HomeScreen(modifier = Modifier, bookShelfViewModel.bookShelfUiState)
        }
    }
}

