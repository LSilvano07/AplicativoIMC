package com.IMC.myapplication

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.IMC.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener(this)


        }

    override fun onClick(view: View) {
        if (view.id == R.id.button_calcular){
            calculate()
        }
    }

    private fun calculate(){
        val peso = binding.editPeso.text.toString().toFloat()
        val altura = binding.editAltura.text.toString().toFloat()

        val calcucloimc = peso/(altura * altura)
        if (calcucloimc < 16){
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Magreza Grau III"

        }else if(calcucloimc >= 16 && calcucloimc <= 16.9){
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Magreza Grau II"
        }else if(calcucloimc >= 17 && calcucloimc <= 18.4){
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Magreza Grau I"
        }else if(calcucloimc >= 18.5 && calcucloimc <= 24.9) {
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Adequado"
        }else if(calcucloimc >= 25 && calcucloimc <= 29.9) {
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Pré-Obeso"
        }else if(calcucloimc >= 30 && calcucloimc <= 34.9) {
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Obesidade Grau I"
        }else if(calcucloimc >= 35 && calcucloimc <= 39.9) {
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Obesidade Grau II"
        }else if(calcucloimc >= 40) {
            binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
            binding.textClassificacao.text = "Classificação: Obesidade Grau III"
        }
        //Toast Notification
        //Toast.makeText(this, calculoimcstr, Toast.LENGTH_SHORT).show()
        //binding.textResultadoimc.text = "${"%.2f".format(calcucloimc)}"
    }


}
