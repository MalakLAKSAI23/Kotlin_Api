package com.example.EntConvert
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar


object UserEntity: Table<Nothing>("user") {
    val id = int("id").primaryKey()
    val login = varchar("login")
    val password = varchar("password")
    val role = varchar("role")
}