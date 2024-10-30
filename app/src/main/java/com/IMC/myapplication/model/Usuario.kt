package com.IMC.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_table")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String,
    val altura: Double,
    val peso: Double
)

