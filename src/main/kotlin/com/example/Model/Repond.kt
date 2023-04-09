package com.example.Model

import kotlinx.serialization.Serializable

@Serializable
data class Repond<User>(
    var user:User
)
