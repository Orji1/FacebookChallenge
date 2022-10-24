package com.example.myapplication.common // ktlint-disable filename

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun rememberSvgPainter(data: Any?) =
    rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            // .decoderFactory(SvgDecoder.Factory())
            .data(data)
            .build()
    )
