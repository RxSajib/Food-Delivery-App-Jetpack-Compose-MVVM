package com.food.zone

import android.os.Bundle
import android.telecom.Call.Details
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.food.zone.presentation.AccountScreen
import com.food.zone.presentation.CDetails
import com.food.zone.presentation.HomeScreen
import com.food.zone.presentation.SignInScreen
import com.food.zone.presentation.SignUpScreen
import com.food.zone.presentation.screen.AuthScreen
import com.food.zone.presentation.screen.CategoryDetailsScreen
import com.food.zone.presentation.screen.HomeScreen
import com.food.zone.presentation.screen.SignInAccount
import com.food.zone.presentation.screen.SignUpScreen
import com.food.zone.ui.theme.FoodZoneTheme
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
            setKeepOnScreenCondition {
                showSplashScreen
            }
        }

        lifecycleScope.launch {
            delay(2000)
            showSplashScreen = false
        }
        setContent {
            FoodZoneTheme {
                Scaffold { padding ->
                    val controller = rememberNavController()
                    NavHost(
                        navController = controller,
                        startDestination = HomeScreen,
                        modifier = Modifier.padding(padding),
                        enterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { 1000 },
                                animationSpec = tween(durationMillis = 300)
                            ) // Faster
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { -1000 },
                                animationSpec = tween(durationMillis = 300)
                            )
                        },
                        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
                        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }

                    ) {
                        composable<AccountScreen> {
                            AuthScreen(signInClick = {
                                controller.navigate(SignInScreen)
                            })
                        }

                        composable<SignInScreen> {
                            SignInAccount(signInSuccess = {
                                controller.navigate(HomeScreen){
                                    popUpTo(0){
                                        inclusive = true
                                    }
                                }
                            })
                        }

                        composable<SignUpScreen> {
                            SignUpScreen()
                        }
                        composable<HomeScreen> {
                            HomeScreen(gotoHome = {
                                controller.navigate(CDetails(name = it.name))
                            })
                        }

                        composable<CDetails> {
                            val args = it.toRoute<CDetails>()
                            CategoryDetailsScreen(args.name)
                        }
                    }
                }

            }
        }
    }
}

