//package com.example.ctf.data.local
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import com.example.ctf.data.local.entities.CTFDao
//import com.example.ctf.data.local.entities.Chat
//
//@Database(
//    entities = [Chat::class],
//    version = 1,
//    exportSchema = false
//)
//abstract class CTFDatabase : RoomDatabase(){
//    abstract fun ctfDao() : CTFDao
//}