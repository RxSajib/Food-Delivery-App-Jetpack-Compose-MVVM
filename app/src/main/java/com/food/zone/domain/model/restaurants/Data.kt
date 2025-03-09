package com.food.zone.domain.model.restaurants


import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize


@Serializable
@kotlinx.parcelize.Parcelize
data class Data(
    val address: String?,
    val categoryId: String?,
    val createdAt: String?,
    val distance: Double?,
    val id: String?,
    val imageUrl: String?,
    val latitude: Double?,
    val longitude: Double?,
    val name: String?,
    val ownerId: String?
) : Parcelable