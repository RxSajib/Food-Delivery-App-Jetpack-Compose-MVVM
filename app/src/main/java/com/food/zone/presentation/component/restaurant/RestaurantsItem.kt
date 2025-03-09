package com.food.zone.presentation.component.restaurant

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.food.zone.R
import com.food.zone.domain.model.restaurants.Data

private const val TAG = "RestaurantsItem"

@Composable
fun RestaurantItem(data: Data, onClick: (Data) -> Unit) {
    Log.d(TAG, "RestaurantItem: $data")
    Box(
        modifier = Modifier
            .width(240.dp)
            .height(160.dp)
            .padding(5.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            .clickable {
                onClick.invoke(data)
            }
            .shadow(elevation = 5.dp,
                shape = RoundedCornerShape(size = 10.dp),
                ambientColor = Color.Gray.copy(alpha = 0.5f),
                spotColor = Color.Gray.copy(alpha = 0.5f)
            )
    ) {


        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = data.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(6f),
                error = painterResource(R.drawable.placeholder),
                placeholder = painterResource(R.drawable.placeholder)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(4f)
                    .background(color = Color.Black.copy(alpha = 0.3f))
                    .background(color = Color.White)
                    .clip(shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(7.dp), verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = data.name?: "Unknown name",
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.W500,
                            fontSize = 15.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.time_svgrepo_com),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                colorFilter = ColorFilter.tint(color = Color.Gray.copy(alpha = 0.5f))
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Free Delivery",
                                style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.5f),
                                    fontSize = 12.sp
                                )
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.delivery_svgrepo_com),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                colorFilter = ColorFilter.tint(color = Color.Gray.copy(alpha = 0.5f))
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Fast Delivery",
                                style = TextStyle(
                                    color = Color.Gray.copy(alpha = 0.5f),
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(6.dp)
                .clip(
                    shape = RoundedCornerShape(size = 15.dp)
                )
                .background(color = Color.White)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "4.5",
                style = TextStyle(fontSize = 12.sp)
            )
            Spacer(modifier = Modifier.width(2.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    modifier = Modifier.size(12.dp),
                    colorFilter = ColorFilter.tint(color  = colorResource(R.color.orange))
                )
                Text(
                    text = "(25)",
                    style = TextStyle(fontSize = 10.sp, color = colorResource(R.color.orange))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    RestaurantItem(
        data = Data(
            id = "dc5d7164-de9b-43e2-91c1-151fbeb50a12",
            ownerId = "315c11ca-3f9d-4ea2-8439-01613228b4c3",
            name = "Coffee Corner",
            address = "987 Cedar St, San Francisco, CA",
            categoryId = "b7df5977-75a8-4c69-bb6f-9bfe9df0706f",
            latitude = 40.712776,
            imageUrl = "https://insanelygoodrecipes.com/wp-content/uploads/2020/07/Cup-Of-Creamy-Coffee.png",
            longitude = -74.005977,
            createdAt = "2025-03-04T13:54:59.216485",
            distance = 0.0032984534242372137
        ),
        onClick = {}
    )
}