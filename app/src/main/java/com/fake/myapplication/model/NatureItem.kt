package com.fake.myapplication.model

import java.io.Serializable

data class NatureItem(
    val id: Int,
    val title: String,
    val description: String,
    val imageResId: Int
) : Serializable
