package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R

@Composable
fun HomeScreen(modifier: Modifier, bookShelfUiState: BookShelfViewModel.BookShelfUiState) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (bookShelfUiState) {
            is BookShelfViewModel.BookShelfUiState.Start -> StartScreen(modifier = modifier)
            is BookShelfViewModel.BookShelfUiState.Loading -> LoadingScreen(modifier = modifier)
            is BookShelfViewModel.BookShelfUiState.Error -> ErrorScreen(modifier = modifier)
            is BookShelfViewModel.BookShelfUiState.Success -> BookCardsGrid(
                listOfBookUrl = bookShelfUiState.booksUrl, modifier = modifier
            )
        }
    }
}

@Composable
fun SearchTopAppBar(
    searchText: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onKeyBoardSearch: () -> Unit
) {
    TopAppBar(modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { onSearchChange(it) },
            shape = RoundedCornerShape(100.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.search_icon_description)
                )
            },
            label = { Text(text = stringResource(id = R.string.search_label)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onKeyBoardSearch() })
        )
    }
}

@Composable
fun BookCard(bookUrl: String, modifier: Modifier = Modifier) {
    Card(modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(bookUrl)
                .crossfade(true)
                .build(),
            contentDescription = "book cover",
            placeholder = painterResource(id = R.drawable.loading_img)
        )
    }

}

@Composable
fun BookCardsGrid(listOfBookUrl: List<String?>, modifier: Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(listOfBookUrl) {
            if (it != null) {
                BookCard(bookUrl = it)
            }
        }
    }

}

@Composable
fun StartScreen(modifier: Modifier) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.start_screen))
    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.loading_failed))
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.loading)
        )
    }
}



