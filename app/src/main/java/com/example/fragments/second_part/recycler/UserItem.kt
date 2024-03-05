package com.example.fragments.second_part.recycler

import androidx.annotation.DrawableRes
import java.io.Serializable

data class UserItem(
    val userId: Int,
    val userName: String,
    val userSurname: String,
    val userPhone: String,
    @DrawableRes
    val imageRes: Int
) : Serializable