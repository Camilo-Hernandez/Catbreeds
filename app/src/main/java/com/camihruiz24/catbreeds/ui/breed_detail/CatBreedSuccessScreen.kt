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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.camihruiz24.catbreeds.R
import com.camihruiz24.catbreeds.data.ItemModel
import com.camihruiz24.catbreeds.ui.breeds_list.ItemImage


@Composable
fun CatBreedSuccessScreen(itemModel: ItemModel, onAddToFavorites: () -> Unit) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(state = scrollState),
    ) {
        Text(
            text = itemModel.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
        )
        ItemImage(itemModel, Modifier.align(Alignment.CenterHorizontally))
        Text(
            text = stringResource(id = R.string.attributes),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            itemModel.attributes
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
            text = itemModel.description,
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

