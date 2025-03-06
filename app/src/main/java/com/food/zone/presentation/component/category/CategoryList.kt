package com.food.zone.presentation.component.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.food.zone.domain.model.category.Data

@Composable
fun CategoryList(category : List<Data>, click: (Data) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()){
        LazyRow {
            itemsIndexed(category){ index, item ->
                CateGoryItem(
                    category = item,
                    onClick = {
                        click.invoke(it)
                    }
                )
            }
        }
    }
}