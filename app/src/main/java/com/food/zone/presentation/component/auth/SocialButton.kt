package com.food.zone.presentation.component.auth


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.food.zone.R
import kotlinx.coroutines.flow.Flow

@Composable
fun SocialButton(icon: Int, button_title: String, bg_color: Color, onclick: () -> Unit, width : Dp) {

    Button(
        onClick = {
            onclick.invoke()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = bg_color
        ),
        modifier = Modifier.width(width)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = button_title, style = TextStyle(color = Color.White, fontSize = 12.sp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SocialButton(
        icon = R.drawable.facebook_icon,
        button_title = "Facebook",
        bg_color = Color.Blue,
        onclick = {},
        width = 200.dp
    )
}