package com.camihruiz24.catbreeds.ui.breed_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.camihruiz24.catbreeds.data.CatBreed


@Composable
fun CatBreedSuccessScreen(catBreed: CatBreed, onAddToFavorites: () -> Unit) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(state = scrollState),
    ) {
        Text(
            text = catBreed.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                // TODO: Eliminar la cadena quemada. Investigar si con retrofit puedo manejar este link.
                .data(
                    "https://cdn2.thecatapi.com/images/${catBreed.imageId}.jpg"
                )
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.mipmap.cat_icon),
            contentDescription = stringResource(R.string.cat_cover),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.attributes),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            catBreed.attributes
                .split(", ")
                .forEach { attribute ->
                    Text(
                        text = attribute,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 8.dp
                        )
                    )
                }
        }
        Text(
            text = catBreed.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = onAddToFavorites,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = stringResource(id = R.string.add_to_favorites))
        }
    }
}

