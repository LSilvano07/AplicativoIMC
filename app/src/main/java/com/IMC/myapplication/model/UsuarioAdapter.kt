package com.IMC.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.IMC.myapplication.model.UsuarioEntity

class UsuarioAdapter(
    private var usuarios: List<UsuarioEntity>, // Lista de usuários
    private val onRemove: (UsuarioEntity) -> Unit // Função de remoção
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    class UsuarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.textNome)
        val alturaTextView: TextView = itemView.findViewById(R.id.textAltura)
        val pesoTextView: TextView = itemView.findViewById(R.id.textPeso)
        val imcTextView: TextView = itemView.findViewById(R.id.textImc)
        val removeButton: Button = itemView.findViewById(R.id.buttonRemove) // Referência ao botão
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.nomeTextView.text = usuario.nome
        holder.alturaTextView.text = "Altura: ${usuario.altura}"
        holder.pesoTextView.text = "Peso: ${usuario.peso}"


        // Configurar o listener do botão de remover
        holder.removeButton.setOnClickListener {
            onRemove(usuario) // Chama a função de remoção
        }
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }

    // Método para atualizar a lista de usuários
    fun atualizarLista(novosUsuarios: List<UsuarioEntity>) {
        usuarios = novosUsuarios
        notifyDataSetChanged()
    }
}