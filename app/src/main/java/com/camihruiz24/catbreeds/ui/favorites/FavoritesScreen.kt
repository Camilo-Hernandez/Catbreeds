package com.camihruiz24.catbreeds.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.camihruiz24.catbreeds.R

// Una función que crea una lista fija de 5 objetos FavoriteItem con datos predefinidos
val fixedFavorites: List<FavoriteItem> = listOf(
    FavoriteItem(
        id = 0,
        title = "El principito",
        description = "Un clásico de la literatura infantil y juvenil",
        imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51ESTgQDduL._SX331_BO1,204,203,200_.jpg"
    ),
    FavoriteItem(
        id = 1,
        title = "Harry Potter y la piedra filosofal",
        description = "La primera aventura del joven mago en Hogwarts",
        imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51ifu1aebKL._SX331_BO1,204,203,200_.jpg"
    ),
    FavoriteItem(
        id = 2,
        title = "El código Da Vinci",
        description = "Un thriller que mezcla historia, arte y religión",
        imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51Z0nLAfLML._SX331_BO1,204,203,200_.jpg"
    ),
    FavoriteItem(
        id = 3,
        title = "Cien años de soledad",
        description = "La saga familiar de los Buendía a lo largo de varias generaciones",
        imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51o5dnjkzsL._SX331_BO1,204,203,200_.jpg"
    ),
    FavoriteItem(
        id = 4,
        title = "El señor de los anillos",
        description = "La épica trilogía de fantasía creada por J.R.R. Tolkien",
        imageUrl = "https://images-na.ssl-images-amazon.com/images/I/51eq24cRtRL._SX331_BO1,204,203,200_.jpg"
    )
)


@Preview
@Composable
fun FavoritesListPreview() {
    FavoritesList(favorites = fixedFavorites)
}

// Una clase de datos que representa un item de favoritos
data class FavoriteItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageUrl: String,
)

// Una función componible que muestra una lista de favoritos usando LazyColumn
@Composable
fun FavoritesList(favorites: List<FavoriteItem>) {
    // Usa LazyColumn para mostrar la lista de forma eficiente
    LazyColumn {
        // Itera sobre los items de favoritos y usa la función item() para añadir cada uno al LazyColumn
        items(favorites) { favorite ->
            // Llama a la función FavoriteCard que muestra el contenido de cada item de favoritos
            FavoriteCard(favorite)
        }
    }
}

// Una función componible que muestra el contenido de un item de favoritos usando Card, Row y Column
@Composable
fun FavoriteCard(favorite: FavoriteItem) {
    // Usa un estado mutable para guardar si el item está marcado como favorito o no
    var isFavorite by remember { mutableStateOf(false) }
    // Usa Card para crear un fondo con esquinas redondeadas y sombra
    Card(
        modifier = Modifier
            .fillMaxWidth() // Ocupa el ancho disponible
            .padding(8.dp), // Añade un padding alrededor de la Card
        elevation = CardDefaults.cardElevation(4.dp), // Añade una sombra con una elevación de 4.dp
        shape = RoundedCornerShape(8.dp) // Define la forma de la Card con esquinas redondeadas de 8.dp
    ) {
        // Usa Row para colocar los elementos horizontalmente dentro de la Card
        Row(
            modifier = Modifier.padding(8.dp), // Añade un padding alrededor de la Row
            verticalAlignment = Alignment.CenterVertically // Alinea verticalmente los elementos al centro
        ) {
            // Usa Image para mostrar la foto circular del item
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://images-na.ssl-images-amazon.com/images/I/51eq24cRtRL._SX331_BO1,204,203,200_.jpg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.mipmap.cat_icon),
                contentDescription = stringResource(R.string.cat_cover),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp)
                    .requiredSizeIn(maxHeight = 60.dp)
            )
            // Usa Spacer para añadir un espacio horizontal entre la imagen y la columna
            Spacer(modifier = Modifier.width(8.dp))
            // Usa Column para colocar los elementos verticalmente dentro de la Row
            Column(
                modifier = Modifier.weight(1f), // Ocupa el espacio horizontal disponible menos el de la imagen y el icono
                verticalArrangement = Arrangement.SpaceBetween // Distribuye el espacio vertical entre los elementos
            ) {
                // Usa Text para mostrar el título del item
                Text(
                    text = favorite.title,
                    style = MaterialTheme.typography.titleSmall, // Usa el estilo tipográfico h6 definido en el tema actual
                    maxLines = 1, // Muestra como máximo una línea de texto
                    overflow = TextOverflow.Ellipsis // Usa puntos suspensivos si el texto no cabe en una línea
                )
                // Usa Text para mostrar la descripción del item
                Text(
                    text = favorite.description,
                    style = MaterialTheme.typography.bodyMedium, // Usa el estilo tipográfico body2 definido en el tema actual
                    maxLines = 2, // Muestra como máximo dos líneas de texto
                    overflow = TextOverflow.Ellipsis // Usa puntos suspensivos si el texto no cabe en dos líneas
                )
            }
            // Usa Spacer para añadir un espacio horizontal entre la columna y el icono
            Spacer(modifier = Modifier.width(8.dp))
            // Usa IconButton para mostrar un icono de 3 puntos o un icono de corazón rojo al darle click
            IconButton(
                onClick = {
                    isFavorite = !isFavorite
                } // Cambia el estado de favorito al pulsar el botón
            ) {
                // Usa Icon para mostrar el icono adecuado según el estado de favorito
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Default.MoreVert, // Usa el icono de favorito o el de más opciones según el estado
                    contentDescription = null, // El icono es decorativo, no necesita descripción de contenido
                    tint = if (isFavorite) Color.Red else LocalContentColor.current // Usa el color rojo si es favorito o el color de contenido local si no lo es
                )
            }
        }
    }
}
