package com.example.plugins

import com.example.Db.Connect
import com.example.EntConvert.UserEntity
import com.example.Model.Repond
import com.example.Model.User
import com.example.Model.UserRequest
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import org.ktorm.dsl.*

fun Application.configureRouting() {
    routing {
        val db = Connect.connecter

        post("/adduser") {
            val request = call.receive<UserRequest>()
            val result = db.insert(UserEntity){
                set(it.login, request.login)
                set(it.password, request.password)
                set(it.role, request.role)
            }
            if(result == 1) {
                call.respond(HttpStatusCode.OK)
            }else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
        delete("/user/{id}") {
            var id = call.parameters["id"]?.toInt()?:-1
            var deletuser = db.delete(UserEntity) {
                it.id eq id
            }
            if(deletuser == 1 ) {
                call.respond(HttpStatusCode.OK)
            }else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/") {
            val users = db.from(UserEntity).select().map {
                val id = it[UserEntity.id]
                val login = it[UserEntity.login]
                val password = it[UserEntity.password]
                val role = it[UserEntity.role]
                User(id?:-1,login?:"", password?:"",role?:"")
            }
            call.respond(users)
        }
        put("/user/{id}"){
            val id = call.parameters["id"]?.toInt()?:-1
            val updatebook = call.receive<UserRequest>()
            val uppd = db.update(UserEntity){
                set(it.login, updatebook.login)
                set(it.password, updatebook.password)
                set(it.role, updatebook.role)
                where { it.id eq id }
            }
            if(uppd == 1) {
                call.respond(HttpStatusCode.OK)
            }else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/user/{id}") {
            var id = call.parameters["id"]?.toInt() ?:-1
            val user = db.from(UserEntity).select()
                .where(UserEntity.id eq id)
                .map {
                    val id = it[UserEntity.id]
                    val login = it[UserEntity.login]
                    val password = it[UserEntity.password]
                    val role = it[UserEntity.role]
                    User(id?:-1,login?:"", password?:"",role?:"")

                }.firstOrNull()
            if(user == null) {
                call.respond(HttpStatusCode.NotFound)
            }else {
                call.respond(HttpStatusCode.OK,Repond(user))
            }
        }
    }

}
