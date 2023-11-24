package com.misfinanzaspersonales.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misfinanzaspersonales.R
import com.misfinanzaspersonales.models.entities.ConceptoEntity

class ConceptoListAdapter : ListAdapter<ConceptoEntity, ConceptoListAdapter.ConceptoViewHolder>(ConceptosComparator()) {

    /*
    WordListAdapter crea el WordViewHolder en onCreateViewHolder y lo vincula en onBindViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConceptoViewHolder {
        return ConceptoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ConceptoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.concepto_tx_pk)
    }

    /*
    El ViewHolder que mostrará cada palabra de nuestra lista.
    La clase ConceptoViewHolder, que nos permite vincular un texto a un TextView.
    Esta muestra una función create() estática que controla el aumento del diseño.
     */
    class ConceptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val conceptoItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            conceptoItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): ConceptoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ConceptoViewHolder(view)
            }
        }
    }

    /*
    WordsComparator define cómo calcular si dos palabras son iguales o si los contenidos son los mismos.
     */
    class ConceptosComparator : DiffUtil.ItemCallback<ConceptoEntity>() {
        override fun areItemsTheSame(oldItem: ConceptoEntity, newItem: ConceptoEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ConceptoEntity, newItem: ConceptoEntity): Boolean {
            return oldItem.concepto_tx_pk == newItem.concepto_tx_pk
        }
    }
}