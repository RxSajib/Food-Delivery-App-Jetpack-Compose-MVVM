package com.food.zone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.lifecycleScope
import com.food.zone.presentation.screen.AuthScreen
import com.food.zone.presentation.screen.SignInAccount
import com.food.zone.ui.theme.FoodZoneTheme
import com.food.zone.ui.theme.fontFamily
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var showSplashScreen = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                showSplashScreen
            }
        }

        lifecycleScope.launch {
            delay(2000)
            showSplashScreen = false
        }
        setContent {
            FoodZoneTheme {
                SignInAccount()
            }
        }
    }
}

