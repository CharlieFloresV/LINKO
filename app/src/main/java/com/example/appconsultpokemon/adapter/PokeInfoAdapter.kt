package com.example.appconsultpokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appconsultpokemon.data.model.Pokemon
import com.example.appconsultpokemon.databinding.ItemPokeNameBinding

class PokeInfoAdapter(
    private val pokemonList: List<Pokemon>,
    private val itemClickListener: OnPokemonClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPokemonClickListener{
        fun onPokemonClick(pokemon: Pokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ItemPokeNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PokemonsViewHolder(itemBinding)

        itemBinding.root.setOnClickListener{
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener

            itemClickListener.onPokemonClick((pokemonList[position]))
        }

        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is PokemonsViewHolder -> holder.bind(pokemonList[position])
        }
    }

    override fun getItemCount(): Int = pokemonList.size

    private inner class PokemonsViewHolder(
        val binding: ItemPokeNameBinding
    ): BaseViewHolder<Pokemon>(binding.root){
        override fun bind(item: Pokemon) {
            binding.tvPokemonName.text = item.name
        }
    }

}