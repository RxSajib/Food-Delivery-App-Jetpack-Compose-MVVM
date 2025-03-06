package com.food.zone.presentation.component.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.food.zone.R
import com.food.zone.domain.model.category.Data

@Composable
fun CateGoryItem(category: Data, onClick: (Data) -> Unit) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp)
            .height(95.dp)
            .width(65.dp)
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(45.dp)
            )
            .background(color = Color.White)
            .clip(shape = RoundedCornerShape(size = 45.dp))
            .clickable {
                onClick.invoke(category)
            }
            .padding(5.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = category.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(size = 40.dp)
                .clip(shape = CircleShape),
            placeholder = painterResource(R.drawable.placeholder),
            error =  painterResource(R.drawable.placeholder)
        )
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Text(
            text = category.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 12.sp, color = Color.Black),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CateGoryItem(
        category = Data(
            id = "9c67e4f9-8ba6-431a-9b0c-c48bd42ad3c7",
            name = "Fast Food",
            imageUrl = "https://www.pngarts.com/files/3/Fast-Food-Free-PNG-Image.png",
            createdAt = "2025-03-04T13:54:59.200462"
        ),
        onClick = {}
    )
}