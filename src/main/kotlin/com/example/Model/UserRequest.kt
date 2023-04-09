package com.example.Model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val login:String,
    val password:String,
    val role:String
)
