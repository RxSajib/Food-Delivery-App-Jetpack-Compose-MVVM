package com.food.zone.presentation.component.auth

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.food.zone.R

@Composable
fun TextInputLayout(
    title : String,
    onValueChange: (String) -> Unit,
    value: String,
    ledingIcon: Int,
    trailingIcon: Int? = null,
    isInputPassword: Boolean,
    showPasswordClick: () -> Unit
) {


    Column {
        Text(
            text = title,
            style = TextStyle(color = Color.Black.copy(alpha = 0.5f)),
            modifier = Modifier.padding(vertical = 10.dp)
        )

        OutlinedTextField(
            onValueChange = { onValueChange.invoke(it) },
            value = value,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isInputPassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.Black.copy(alpha = 0.5f),
                focusedTextColor = Color.Black,
                focusedBorderColor = Color.Red,
                unfocusedBorderColor = Color.Black.copy(alpha = 0.5f)
            ),
            shape = CircleShape,
            leadingIcon = {
                Icon(painter = painterResource(ledingIcon), contentDescription = null)
            },
                trailingIcon = {
                    if (trailingIcon != null) {
                        IconButton(onClick = {
                            showPasswordClick.invoke()
                        }){
                            Icon(painter = painterResource(trailingIcon), contentDescription = null)
                        }
                    }

            }


        )
    }


}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    var data by remember { mutableStateOf("") }
    TextInputLayout(
        title = "Enter your email",
        value = data,
        onValueChange = {
            data = it
        },
        ledingIcon = R.drawable.user_svgrepo_com,
        trailingIcon = R.drawable.user_svgrepo_com,
        isInputPassword = false,
        showPasswordClick = {}
    )
}