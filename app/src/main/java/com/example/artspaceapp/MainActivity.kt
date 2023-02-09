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
    val MIN = 1
    val MAX = 5
    var current by remember { mutableStateOf(1) }


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

    when (current) {
        MIN -> {
            ArtSpaceScreen(
                imageId = R.drawable.art_1,
                contentDescription = R.string.content_description_1,
                title = R.string.artwork_title_1,
                artist = R.string.artwork_artist_1,
                year = R.string.artwork_year_1,
                onPrevious = onPrevious,
                onNext = onNext
            )
        }
        2 -> {
            ArtSpaceScreen(
                imageId = R.drawable.art_2,
                contentDescription = R.string.content_description_2,
                title = R.string.artwork_title_2,
                artist = R.string.artwork_artist_2,
                year = R.string.artwork_year_2,
                onPrevious = onPrevious,
                onNext = onNext
            )
        }
        3 -> {
            ArtSpaceScreen(
                imageId = R.drawable.art_3,
                contentDescription = R.string.content_description_3,
                title = R.string.artwork_title_3,
                artist = R.string.artwork_artist_3,
                year = R.string.artwork_year_3,
                onPrevious = onPrevious,
                onNext = onNext
            )
        }
        4 -> {
            ArtSpaceScreen(
                imageId = R.drawable.art_4,
                contentDescription = R.string.content_description_4,
                title = R.string.artwork_title_4,
                artist = R.string.artwork_artist_4,
                year = R.string.artwork_year_4,
                onPrevious = onPrevious,
                onNext = onNext
            )
        }
        MAX -> {
            ArtSpaceScreen(
                imageId = R.drawable.art_5,
                contentDescription = R.string.content_description_5,
                title = R.string.artwork_title_5,
                artist = R.string.artwork_artist_5,
                year = R.string.artwork_year_5,
                onPrevious = onPrevious,
                onNext = onNext
            )
        }
    }
}

@Composable
fun ArtSpaceScreen(
    imageId: Int,
    contentDescription: Int,
    title: Int,
    artist: Int,
    year: Int,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
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
                imageId = imageId,
                contentDescription = contentDescription
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ArtworkDescriptor(title = title, artist = artist, year = year)
        }
        DisplayController(onPrevious = onPrevious, onNext = onNext)
    }
}

@Composable
fun ArtworkWall(
    imageId: Int,
    contentDescription: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = stringResource(id = contentDescription),
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
    title: Int,
    artist: Int,
    year: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .shadow(elevation = 2.dp, spotColor = Color.Gray)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = title),
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = artist))
                }
                append("(${stringResource(id = year)})")
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