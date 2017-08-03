package com.tnovoselec.android.pokemon.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.tnovoselec.android.pokemon.R
import com.tnovoselec.android.pokemon.ui.viewmodel.PokemonViewModel


class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    var pokemonList = mutableListOf<PokemonViewModel>()

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonListAdapter.PokemonViewHolder?, position: Int) {
        holder?.bindViews(pokemonList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonListAdapter.PokemonViewHolder {
        return PokemonViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_pokemon, parent, false))
    }

    fun updateData(pokemonList: List<PokemonViewModel>) {
        this.pokemonList.clear()
        this.pokemonList.addAll(pokemonList)
        notifyDataSetChanged()
    }

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.pokemon_image) lateinit var pokemonImage: ImageView
        @BindView(R.id.pokemon_name) lateinit var pokemonName: TextView

        fun bindViews(pokemonViewModel: PokemonViewModel) {
            ButterKnife.bind(this, itemView)
            pokemonName.text = pokemonViewModel.name
        }
    }
}