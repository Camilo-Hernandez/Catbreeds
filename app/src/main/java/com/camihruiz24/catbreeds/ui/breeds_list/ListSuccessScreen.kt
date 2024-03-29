package com.camihruiz24.catbreeds.ui.breeds_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.camihruiz24.catbreeds.data.ItemModel

@Composable
fun ListSuccessScreen(
    modifier: Modifier = Modifier,
    itemsList: List<ItemModel>,
    onNavigateToDetail: (ItemModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium,
                modifier = modifier.padding(vertical = 8.dp)
            )
        }
        items(itemsList) { item ->
            ItemCard(
                itemModel = item,
                onNavigateToDetail = { onNavigateToDetail(item) }
            )
        }
    }
}

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    itemModel: ItemModel,
    onNavigateToDetail: () -> Unit,
) { // cat: Cat
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onNavigateToDetail),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier.padding(16.dp),
        ) {
            Row {
                Text(
                    text = itemModel.name,
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
            ItemImage(itemModel, Modifier.align(Alignment.CenterHorizontally))
            Row {
                Text(
                    text = "País de origen: ${itemModel.origin}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(2f)
                )
                Text(
                    text = itemModel.attributes.split(", ").random(),
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

@Composable
internal fun ItemImage(itemModel: ItemModel, modifier: Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            // TODO: Eliminar la cadena quemada. Investigar si con retrofit puedo manejar este link.
            .data("https://cdn2.thecatapi.com/images/${itemModel.imageId}.jpg")
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.mipmap.cat_icon),
        error = painterResource(id = R.mipmap.cat_icon),
        fallback = painterResource(id = R.mipmap.cat_icon),
        contentDescription = stringResource(R.string.cat_cover),
        contentScale = ContentScale.Fit,
        modifier = modifier
            .padding(16.dp)
            .requiredSizeIn(minWidth = 400.dp, minHeight = 320.dp)
    )
}

