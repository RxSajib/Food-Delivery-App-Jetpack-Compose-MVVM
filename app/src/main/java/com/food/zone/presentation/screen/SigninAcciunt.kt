package com.food.zone.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.food.zone.R
import com.food.zone.data.model.signin_data.SignUpData
import com.food.zone.presentation.component.auth.SocialButton
import com.food.zone.presentation.component.auth.TextInputLayout
import com.food.zone.presentation.viewmodel.SignUpViewModel
import com.food.zone.utils.NetworkResult

@Composable
fun SignInAccount(signInViewModel : SignUpViewModel = hiltViewModel(), signInSuccess : () -> Unit) {

    val userName = signInViewModel.name.collectAsStateWithLifecycle()
    val userEmail = signInViewModel.email.collectAsStateWithLifecycle()
    val userPassword = signInViewModel.password.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val signIn = signInViewModel.signinStateFlow.collectAsState()
    when(signIn.value){
        is NetworkResult.Empty -> {

        }
        is NetworkResult.Loading -> {
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
        is NetworkResult.Error -> {

        }
        is NetworkResult.Success -> {
            signInSuccess.invoke()
            Toast.makeText(context, "Loading Success ${signIn.value.data?.token}", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {


                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.applogo),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = "SignUp Account",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextInputLayout(
                        onValueChange = {
                            signInViewModel.saveName(it)
                        },
                        value = userName.value,
                        title = "Enter your name",
                        ledingIcon = R.drawable.user_svgrepo_com,
                        isInputPassword = false,
                        showPasswordClick = {}
                    )
                    TextInputLayout(
                        onValueChange = {
                            signInViewModel.saveEmail(it)
                        },
                        value = userEmail.value,
                        title = "Enter your email",
                        ledingIcon = R.drawable.user_svgrepo_com,
                        isInputPassword = false,
                        showPasswordClick = {}
                    )
                    TextInputLayout(
                        onValueChange = {
                            signInViewModel.savePassword(it)
                        },
                        value = userPassword.value,
                        title = "Enter your password",
                        ledingIcon = R.drawable.user_svgrepo_com,
                        isInputPassword = true,
                        trailingIcon = R.drawable.lock_alt_svgrepo_com,
                        showPasswordClick = {}
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Button(onClick = {
                        signInViewModel.signUpAccount(signUpData = SignUpData(
                            name = userName.value,
                            password = userPassword.value,
                            email = userEmail.value
                        ))
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "SignIn Account"
                        )
                    }
                }

            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color.Black.copy(alpha = 0.7f),
                    thickness = 0.5.dp
                )

                Text(
                    text = "Continue with",
                    style = TextStyle(color = Color.Black),
                    modifier = Modifier.padding(horizontal = 20.dp),
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color.Black.copy(alpha = 0.7f),
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
                    bg_color = Color.Red,
                    width = 150.dp
                )
                SocialButton(
                    icon = R.drawable.google,
                    onclick = {},
                    button_title = "Google",
                    bg_color = Color.Magenta,
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
                    style = TextStyle(color = Color.Black.copy(alpha = 0.5f))
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "SignIn",
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SignInAccount(signInSuccess = {})
}