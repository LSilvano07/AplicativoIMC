package com.IMC.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "tabela_usuario")
data class Usuario (

    @ColumnInfo(name = "first_name") val firstname: String?,
    @ColumnInfo(name = "altura") val altura: String?,
    @ColumnInfo(name = "peso") val peso: String?,

){
    @PrimaryKey(autoGenerate = true)
    var uid:Int = 0

}


