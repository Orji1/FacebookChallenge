package com.example.myapplication.presentation.giphy.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
//import com.example.myapplication.common.rememberSvgPainter
import com.example.myapplication.data.dto.Data
import com.example.myapplication.presentation.giphy.GiphyViewModel

@Composable
fun GiphyScreen (
    viewModel: GiphyViewModel
) {
    val state = viewModel.stateFlow.collectAsState().value.data

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            state?.data?.size?.let { listSize ->
                items(listSize) {
                    GiphyItem(
                        state.data[it]
                    )
                }
            }

        }

    }
}

@Composable
fun GiphyItem(data: Data) {
    Log.v("looking for data", "$data")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            backgroundColor = Color.Gray,
            shape = RoundedCornerShape(8.dp)
                ){
            Column (modifier = Modifier.padding(8.dp)) {
                Text(modifier = Modifier.wrapContentSize(), text = data.title, color = Color.White, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                /*Image(
                    painter = rememberSvgPainter(data.url),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )*/
                AsyncImage(model = data.url, contentDescription = "")

            }

        }

    }

}