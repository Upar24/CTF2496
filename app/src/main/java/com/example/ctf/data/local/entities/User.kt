package com.example.ctf.data.local.entities

import java.util.*


data class User (
    val username: String,
    var password: String,
    var name:String?="",
    var clubName:String?="",
    var ign:String?="",
    var bio:String?="",
    val _id:String= UUID.randomUUID().toString()
)