package com.android.function.recyclerview_api

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.function.databinding.ItemListBinding


class ProfileAdapter (private val context : Context) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(val binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : ProfileData){
            binding.user = data
        }
    }

    var data = listOf<ProfileData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAdapter.ProfileViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(context), parent, false)

        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ProfileViewHolder, position: Int) {
        holder.onBind(data[position])
    }

    override fun getItemCount(): Int = data.size


}