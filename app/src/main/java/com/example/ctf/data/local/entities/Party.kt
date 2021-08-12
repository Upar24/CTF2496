package com.example.ctf.data.local.entities

data class Party(
    val role:String="",
    val no:String="",
    val name:String="",
    val duration:String="",
    var status:String="",
    var check:List<String> = listOf(),
    var nope:List<String> = listOf(),
    var drop:List<String> = listOf(),
    val _id: String=""
)
data class Dropped(
    val role:String="",
    val name:String="",
    val duration:String="",
    val day:String="",
    val _id: String=""
)
data class Today(
    val reguler:String="",
    val ultra:String="",
    val _id: String=""
)
