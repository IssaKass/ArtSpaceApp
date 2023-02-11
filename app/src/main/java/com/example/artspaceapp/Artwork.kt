package com.example.artspaceapp

import androidx.compose.ui.graphics.painter.Painter

class Artwork(
    val image: Painter,
    val contentDescription: String?,
    val title: String,
    val artist: String,
    val year: Int,
) {

}