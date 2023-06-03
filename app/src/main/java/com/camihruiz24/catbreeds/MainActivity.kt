package com.camihruiz24.catbreeds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.camihruiz24.catbreeds.feature_list.presentation.HomeScreen
import com.camihruiz24.catbreeds.feature_list.presentation.CatListViewModel
import com.camihruiz24.catbreeds.ui.theme.CatbreedsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val catListViewModel by viewModels<CatListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatbreedsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(catListUiState =  catListViewModel.uiState)
                }
            }
        }
    }
}