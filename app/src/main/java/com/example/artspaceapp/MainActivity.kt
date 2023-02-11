package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val artworks = arrayOf(
        Artwork(
            image = painterResource(id = R.drawable.art_1),
            contentDescription = stringResource(id = R.string.content_description_1),
            title = stringResource(id = R.string.artwork_title_1),
            artist = stringResource(id = R.string.artwork_artist_1),
            year = stringResource(id = R.string.artwork_year_1).toIntOrNull() ?: 0,
        ), Artwork(
            image = painterResource(id = R.drawable.art_2),
            contentDescription = stringResource(id = R.string.content_description_2),
            title = stringResource(id = R.string.artwork_title_2),
            artist = stringResource(id = R.string.artwork_artist_2),
            year = stringResource(id = R.string.artwork_year_2).toIntOrNull() ?: 0,
        ), Artwork(
            image = painterResource(id = R.drawable.art_3),
            contentDescription = stringResource(id = R.string.content_description_3),
            title = stringResource(id = R.string.artwork_title_3),
            artist = stringResource(id = R.string.artwork_artist_3),
            year = stringResource(id = R.string.artwork_year_3).toIntOrNull() ?: 0,
        ),
        Artwork(
            image = painterResource(id = R.drawable.art_4),
            contentDescription = stringResource(id = R.string.content_description_4),
            title = stringResource(id = R.string.artwork_title_4),
            artist = stringResource(id = R.string.artwork_artist_4),
            year = stringResource(id = R.string.artwork_year_4).toIntOrNull() ?: 0,
        ),
        Artwork(
            image = painterResource(id = R.drawable.art_5),
            contentDescription = stringResource(id = R.string.content_description_5),
            title = stringResource(id = R.string.artwork_title_5),
            artist = stringResource(id = R.string.artwork_artist_5),
            year = stringResource(id = R.string.artwork_year_5).toIntOrNull() ?: 0,
        )
    )

    val MIN = 0
    val MAX = artworks.size - 1
    var current by remember { mutableStateOf(MIN) }


    val onPrevious: () -> Unit = {
        current--
        if (current < MIN) {
            current = MAX
        }
    }

    val onNext: () -> Unit = {
        current++
        if (current > MAX) {
            current = MIN
        }
    }

    ArtSpaceScreen(artwork = artworks[current], onPrevious = onPrevious,onNext = onNext)
}

@Composable
fun ArtSpaceScreen(
    artwork: Artwork,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ArtworkWall(
                image = artwork.image,
                contentDescription = artwork.contentDescription
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ArtworkDescriptor(title = artwork.title, artist = artwork.artist, year = artwork.year)
        }
        DisplayController(onPrevious = onPrevious, onNext = onNext)
    }
}

@Composable
fun ArtworkWall(
    image: Painter,
    contentDescription: String?,
) {
    Image(
        painter = image,
        contentDescription = contentDescription,
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color.Gray
            )
            .padding(32.dp)
    )
}

@Composable
fun ArtworkDescriptor(
    title: String,
    artist: String,
    year: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .shadow(elevation = 2.dp, spotColor = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(artist)
                }
                append("($year)")
            }
        )
    }
}

@Composable
fun DisplayController(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevious,
            modifier = Modifier.width(150.dp)
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        Button(
            onClick = onNext,
            modifier = Modifier.width(150.dp)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceAppTheme {
        ArtSpaceApp()
    }
}