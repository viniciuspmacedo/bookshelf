package com.example.bookshelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.BookShelfApp
import com.example.bookshelf.ui.screens.BookShelfViewModel
import com.example.bookshelf.ui.theme.BookShelfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookShelfTheme {
                val viewModel: BookShelfViewModel = viewModel(factory = BookShelfViewModel.Factory)
                BookShelfApp(viewModel)
            }
        }
    }
}