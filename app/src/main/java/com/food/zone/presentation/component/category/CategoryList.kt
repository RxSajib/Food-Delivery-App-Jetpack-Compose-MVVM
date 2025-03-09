package com.food.zone.presentation.component.category

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import com.food.zone.domain.model.category.Data

@Composable
fun CategoryList(category : List<Data>, click: (Data) -> Unit) {
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