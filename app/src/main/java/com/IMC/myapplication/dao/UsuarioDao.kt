package com.IMC.myapplication.dao

import androidx.room.Dao
import androidx.room.Insert
import com.IMC.myapplication.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    fun inserir(listUsuario:MutableList<Usuario>)

}