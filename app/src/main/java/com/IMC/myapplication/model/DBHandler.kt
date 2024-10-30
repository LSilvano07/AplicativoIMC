package com.IMC.myapplication.model

import android.content.Context
import androidx.room.Room

object DBHandler {

    fun getDb(context: Context): AppDatabase{

        return Room.databaseBuilder(context, AppDatabase::class.java, name = "User_table").allowMainThreadQueries().build()

    }
}