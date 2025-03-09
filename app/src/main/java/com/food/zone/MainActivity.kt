package com.food.zone

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.telecom.Call.Details
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.food.zone.data.BottomNavBarRoute
import com.food.zone.domain.model.restaurants.Data
import com.food.zone.presentation.AppRoute
import com.food.zone.presentation.screen.AuthScreen
import com.food.zone.presentation.screen.CartScreen
import com.food.zone.presentation.screen.CategoryDetailsScreen
import com.food.zone.presentation.screen.HomeScreen
import com.food.zone.presentation.screen.NotificationScreen
import com.food.zone.presentation.screen.RestaurantDetailsScreen
import com.food.zone.presentation.screen.SignInAccount
import com.food.zone.presentation.screen.SignUpScreen
import com.food.zone.ui.theme.FoodZoneTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.Double
import kotlin.String
import kotlin.collections.mapOf
import kotlin.reflect.KClass
import kotlin.reflect.typeOf

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var showSplashScreen = true
    @OptIn(ExperimentalMaterial3Api::class)
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

            val navitem = listOf(
                BottomNavBarRoute.Home,
                BottomNavBarRoute.MyCart,
                BottomNavBarRoute.MyNotification
            )
            val isShowBottomBar = remember { mutableStateOf(false) }


            FoodZoneTheme {
                val controller = rememberNavController()
                val currentRoute = controller.currentBackStackEntryAsState().value?.destination


                Scaffold(

                    bottomBar = {
                        if(isShowBottomBar.value){
                            NavigationBar {
                                navitem.forEach { item ->

                                    val selected = currentRoute?.hierarchy?.any{it.route== item.route::class.qualifiedName} == true

                                    NavigationBarItem(
                                        selected = selected,
                                        label = {
                                            Text(text = item.title, style = TextStyle(color = if(selected) Color.Black else Color.Black.copy(0.4f)))
                                        },
                                        onClick = {
                                            controller.navigate(item.route){
                                                popUpTo(controller.graph.startDestinationId){
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                painter = painterResource(item.icon),
                                                contentDescription = null,
                                                tint = if(selected) Color.White else Color.Black.copy(alpha = 0.4f)
                                            )
                                        },

                                    )
                                }
                            }
                        }

                    }

                ) { padding ->


                    NavHost(
                        navController = controller,
                        startDestination = AppRoute.HomeScreen   ,
                        modifier = Modifier.padding(padding),

                    ) {
                        composable<AppRoute.AccountScreen> {
                            isShowBottomBar.value = false
                            AuthScreen(signInClick = {
                                controller.navigate(AppRoute.SignInScreen)
                            })
                        }

                        composable<AppRoute.SignInScreen> {
                            isShowBottomBar.value = false
                            SignInAccount(signInSuccess = {
                                controller.navigate(AppRoute.HomeScreen) {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            })
                        }

                        composable<AppRoute.SignUpScreen> {
                            isShowBottomBar.value = false
                            SignUpScreen()
                        }
                        composable<AppRoute.HomeScreen> {
                            isShowBottomBar.value = true
                            HomeScreen(
                                gotoHome = {
                                    controller.navigate(AppRoute.CDetails(name = it.name))
                                },
                                gotoRestaurantDetails = {
                                    Log.d(TAG, "onCreate: image url ${it.imageUrl}")
                                    controller.navigate(
                                        AppRoute.RestaurantDetails(
                                            data = Data(
                                                address = it.address,
                                                categoryId = it.categoryId,
                                                createdAt = it.createdAt,
                                                distance = it.distance,
                                                id = it.id,
                                                imageUrl = "asdasdasdasdasdasd",
                                                latitude = 0.0,
                                                longitude = it.longitude ?: 0.0,
                                                name = it.name ?: "Unknown name",
                                                ownerId = it.ownerId ?: ""

                                            ),
                                            imageUrl = it.imageUrl ?: ""
                                        )
                                    )
                                }
                            )
                        }

                        composable<AppRoute.CDetails> {
                            isShowBottomBar.value = false
                            val args = it.toRoute<AppRoute.CDetails>()
                            CategoryDetailsScreen(args.name)
                        }

                        composable<AppRoute.RestaurantDetails>(
                            typeMap = mapOf(
                                typeOf<Data>() to CustomNavType<Data>(
                                    Data::class,
                                    Data.serializer()
                                )
                            )
                        ) {
                            isShowBottomBar.value = false
                            var args = it.toRoute<AppRoute.RestaurantDetails>()
                            RestaurantDetailsScreen(
                                data = args.data,
                                imageUrl = args.imageUrl
                            )
                        }
                        composable<AppRoute.Cart> {
                            isShowBottomBar.value = true
                            CartScreen()
                        }

                        composable<AppRoute.Notification> {
                            isShowBottomBar.value = true
                            NotificationScreen()
                        }
                    }
                }

            }
        }
    }
}

class CustomNavType<T : Parcelable>(
    private val clazz: KClass<T>,
    private val serializer: KSerializer<T>
) : NavType<T>(true) {
    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, clazz.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: T): String {
        return Json.encodeToString(serializer, value)
    }
}

