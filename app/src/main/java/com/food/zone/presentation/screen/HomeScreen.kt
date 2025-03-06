package com.food.zone.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.food.zone.domain.model.category.Data
import com.food.zone.presentation.component.category.CategoryList
import com.food.zone.presentation.viewmodel.HomeScreenVM

private const val TAG = "HomeScreen"
@Composable
fun HomeScreen(gotoHome : (Data) -> Unit, viewmodel : HomeScreenVM = hiltViewModel()) {

    val category = viewmodel.categoryStateFlow.collectAsState()
    val list = category.value

   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(10.dp)
   ) {
       CategoryList(
           category = list.data?.data ?: emptyList(),
           click = {
               gotoHome.invoke(it)
           }
       )
   }
}