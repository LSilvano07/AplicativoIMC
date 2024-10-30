package com.IMC.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.IMC.myapplication.dao.UsuarioDao
import com.IMC.myapplication.databinding.ActivityCadastroImcBinding
import com.IMC.myapplication.model.AppDatabase
import com.IMC.myapplication.model.UsuarioEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Cadastro_IMC : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroImcBinding
    private lateinit var db: AppDatabase
    private lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.recyclerviewHistorico.layoutManager = LinearLayoutManager(this)

        // Obter instância do banco de dados
        db = AppDatabase.getDatabase(this)
        usuarioDao = db.usuarioDao

        // Observar os usuários cadastrados
        usuarioDao.getAllUsers().observe(this, { usuario ->
            binding.recyclerviewHistorico.adapter = UsuarioAdapter(usuario) { usuario ->
                removerUsuario(usuario) // Chama a função de remoção
            }
        })

        // Configurar botão para retornar ao menu principal
        binding.buttonRetornarmenu.setOnClickListener {
            val navegarmenu = Intent(this, MainActivity::class.java)
            startActivity(navegarmenu)
        }
    }

    private fun removerUsuario(usuario: UsuarioEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            usuarioDao.delete(usuario) // Remove o usuário do banco de dados
            val updatedList = usuarioDao.getAllUsers().value // Obtenha a lista atualizada
            withContext(Dispatchers.Main) {
                binding.recyclerviewHistorico.adapter?.notifyDataSetChanged() // Atualiza a RecyclerView
            }
        }
    }
}



