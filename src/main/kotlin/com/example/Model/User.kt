package com.example.Model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id:Int,
    val login:String,
    val password:String,
    val role:String
)
