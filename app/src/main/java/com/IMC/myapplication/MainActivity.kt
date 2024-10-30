package com.IMC.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.IMC.myapplication.dao.UsuarioDao
import com.IMC.myapplication.databinding.ActivityMainBinding
import com.IMC.myapplication.model.AppDatabase
import com.IMC.myapplication.model.UsuarioEntity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var usuarioDao: UsuarioDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this)
        usuarioDao = db.usuarioDao

        // Configurar os ouvintes de clique
        binding.buttonCalcular.setOnClickListener(this)
        binding.buttonGetHistorico.setOnClickListener {
            val navegarCadastro = Intent(this, Cadastro_IMC::class.java)
            startActivity(navegarCadastro)
        }

        // Botão para salvar usuário
        binding.botaopost.setOnClickListener {
            inserirUsuario()
        }
    }

    private fun inserirUsuario() {
        val nome = binding.editName.text.toString()
        val altura = binding.editAltura.text.toString().toDoubleOrNull()
        val peso = binding.editPeso.text.toString().toDoubleOrNull()

        // Verifica se os valores são válidos antes de salvar
        if (nome.isNotEmpty() && altura != null && peso != null) {
            val usuario = UsuarioEntity(nome = nome, altura = altura, peso = peso)

            // Executa a inserção em uma coroutine
            lifecycleScope.launch {
                usuarioDao.inseririmc(usuario)
                Toast.makeText(this@MainActivity, "Usuário inserido!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Insira valores válidos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_calcular) {
            calculate()
        }
    }

    private fun calculate() {
        val peso = binding.editPeso.text.toString().toFloatOrNull()
        val altura = binding.editAltura.text.toString().toFloatOrNull()

        // Verifica se peso e altura são valores válidos
        if (peso == null || altura == null || altura == 0f) {
            binding.textResultadoimc.text = "Insira valores válidos para peso e altura"
            binding.textClassificacao.text = ""
            return
        }

        // Calcula o IMC
        val calculoImc = peso / (altura * altura)
        binding.textResultadoimc.text = "${"%.2f".format(calculoImc)}"

        // Classificação de acordo com o IMC
        val classificacao = when {
            calculoImc < 16 -> "Classificação: Magreza Grau III"
            calculoImc < 17 -> "Classificação: Magreza Grau II"
            calculoImc < 18.5 -> "Classificação: Magreza Grau I"
            calculoImc < 25 -> "Classificação: Adequado"
            calculoImc < 30 -> "Classificação: Pré-Obeso"
            calculoImc < 35 -> "Classificação: Obesidade Grau I"
            calculoImc < 40 -> "Classificação: Obesidade Grau II"
            else -> "Classificação: Obesidade Grau III"
        }

        // Exibe a classificação
        binding.textClassificacao.text = classificacao
    }
}