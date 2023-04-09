package com.example.Db

import org.ktorm.database.Database

object Connect {
    var connecter = Database.connect (
        url = "jdbc:mysql://localhost:3306/malakdb",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = ""
    )
}