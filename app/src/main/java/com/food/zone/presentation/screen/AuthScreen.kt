package com.food.zone.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.IntState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.food.zone.R
import com.food.zone.presentation.component.auth.SkipButton
import com.food.zone.presentation.component.auth.SocialButton

private const val TAG = "AuthScreen"

@Composable
fun AuthScreen() {

    val imageSize = remember {
        mutableStateOf(IntSize.Zero)
    }
    Log.d(TAG, "AuthScreen: ${imageSize.value}")

    val brash = Brush.verticalGradient(
        colors = listOf(
            Color.Transparent,
            Color.Black
        ),
        startY = imageSize.value.height.toFloat() / 3,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Image(painter = painterResource(R.drawable.background), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .onGloballyPositioned {
                    imageSize.value =
                        it.size   //todo যখন ইমেজ স্ক্রিনে উপস্থিত হয়, তখন তার সাইজ imageSize-এ সংরক্ষণ করা হয়।
                }
                .alpha(alpha = 0.7f))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brash)
        )
    }

    SkipButton(
        onClick = {},
        title = "Skip",
        buttonContent = Color.Black,
        buttonContinerColor = Color.White,
        titleColor = Color.Black,
        modifier = Modifier.padding(10.dp)
    )

    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Text(
            text = "Welcome to",
            style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold, color = Color.White)
        )
        Text(
            text = "Food Hub",
            style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold, color = Color.Yellow)
        )
        Text(
            text = "Your favotit",
            style = TextStyle(fontSize = 45.sp, fontWeight = FontWeight.Bold, color = Color.Yellow)
        )
    }


    Box(modifier = Modifier.fillMaxSize()) {


        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd) // এখানে সঠিকভাবে কাজ করবে
                .padding(16.dp)
        ) {


            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color.White.copy(alpha = 0.3f),
                    thickness = 0.5.dp
                )

                Text(
                    text = "Continue with",
                    style = TextStyle(color = Color.White),
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color.White.copy(alpha = 0.3f),
                    thickness = 0.5.dp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {



                SocialButton(
                    icon = R.drawable.facebook_icon,
                    onclick = {},
                    button_title = "Facebook",
                    bg_color = Color.DarkGray,
                    width = 150.dp
                )
                SocialButton(
                    icon = R.drawable.google,
                    onclick = {},
                    button_title = "Google",
                    bg_color = Color.DarkGray,
                    width = 150.dp
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account?",
                    style = TextStyle(color = Color.White.copy(alpha = 0.5f))
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "SignIn",
                    style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold)
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AuthScreen()
}