package com.camihruiz24.catbreeds.feature_list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.camihruiz24.catbreeds.R
import com.camihruiz24.catbreeds.feature_list.domain.Cat

@Composable
fun HomeScreen(catListUiState: CatListUiState) { //cats: List<Cat>

    /*
    FORMA 2
    val catsState = CatsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<CatsState>(
        initialValue = catsState,
        key1 = lifecycle,
        key2 = catsListViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            catsListViewModel.state.collect{
                value = it
            }
        }
    }

    if (!uiState.isLoading){
        Column(
            Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Cat Breeds", style = MaterialTheme.typography.titleLarge)
            LazyColumn {
                items(items = uiState.cats) { cat ->
                    CatCard(cat, Modifier.clickable { navigationController.navigate("CatDetail") })
                }
            }
        }
    }
    */

    when (catListUiState) {
        is CatListUiState.Success -> CatList(catsList = catListUiState.catsList)
        CatListUiState.Error -> ErrorScreen(errorMessage = stringResource(id = R.string.error_message))
        CatListUiState.Loading -> LoadingScreen()
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(16.dp)
        .fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, errorMessage : String) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = errorMessage)
    }
}

@Composable
fun CatList(modifier: Modifier = Modifier, catsList: List<Cat>) {
    LazyColumn(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Catbreeds", style = MaterialTheme.typography.titleLarge)
        }
        items(catsList){cat ->
            CatCard(cat)
        }
    }
}


@Composable
fun CatCard(cat: Cat) { // cat: Cat
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            Modifier.padding(16.dp),
        ) {
            Row() {
                Text(
                    text = "Albino", //cat.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(2f)
                )
                Text(
                    text = "Más...",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(R.drawable.portada)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.bg_image_placeholder),
                contentDescription = "Movie cover",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .requiredSizeIn(minWidth = 400.dp, minHeight = 320.dp)
            )
            Row {
                Text(
                    text = "País de origen: Island", // ${cat.origin}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(2f)
                )
                Text(
                    text = "Inteligencia", // "${cat.feature}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    textAlign = TextAlign.End

                )
            }
        }
    }
}

