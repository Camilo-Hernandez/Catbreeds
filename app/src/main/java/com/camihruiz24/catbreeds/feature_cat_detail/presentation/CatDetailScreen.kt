package com.camihruiz24.catbreeds.feature_cat_detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.camihruiz24.catbreeds.R
import com.camihruiz24.catbreeds.feature_list.data.Cat

@Composable
fun CatDetailScreen(
    //cat: Cat,
    onAddToFavorites: () -> Unit
) {
    val description = """
        Un gato es un animal pequeño y carnívoro, conocido por su elegancia y agilidad. Tienen un pelaje suave y sedoso que puede ser de diferentes colores y patrones. Los gatos son animales independientes y curiosos, y a menudo disfrutan explorando su entorno. 
    """.trimIndent()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Mario!",//cat.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                // TODO: Eliminar la cadena quemada. Investigar si con retrofit puedo manejar este link.
                .data(
                    R.mipmap.cat_icon
                )
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.mipmap.cat_icon),
            contentDescription = stringResource(R.string.cat_cover),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .requiredSizeIn(minWidth = 400.dp, minHeight = 320.dp)
        )
        Text(
            text = "Atributos:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            //cat.temperament
            "Intelligent, Insightful, Affectionate, Playful, Active"
                .split(", ")
                .forEach { attribute ->
                Text(
                    text = attribute,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
                )
            }
        }
        Text(
            text = description,//cat.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = onAddToFavorites,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Añadir a favoritos")
        }
    }
}
