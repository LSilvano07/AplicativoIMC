package com.IMC.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete // Adicione esta importação
import com.IMC.myapplication.model.UsuarioEntity


@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inseririmc(usuario: UsuarioEntity)

    @Query("SELECT * FROM User_table")
    fun getAllUsers(): LiveData<List<UsuarioEntity>>

    @Update
    suspend fun atualizarUsuario(usuario: UsuarioEntity)

    @Delete // Adicione o método para deletar
    suspend fun delete(usuario: UsuarioEntity)

    @Query("SELECT * FROM User_table WHERE id = :usuarioId LIMIT 1")
    suspend fun getUserById(usuarioId: Int): UsuarioEntity?
}