package com.aves.practicaltest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aves.practicaltest.activity.FirstActivity
import com.aves.practicaltest.databinding.ItemUserBinding
import com.aves.practicaltest.iinterface.GenericClickListener
import com.aves.practicaltest.model.UserModel
import com.bumptech.glide.Glide

class UserAdapter(val listener:GenericClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    val userList = mutableListOf<UserModel>()

    class ViewHolder(val binding:ItemUserBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userModel = userList.get(position)
        holder.binding.tvUserName.text = userModel.user?.name
        holder.binding.tvDescirption.text = userModel.description
        Glide.with(holder.binding.profileImage).load(userModel.user?.profileImage?.medium).into(holder.binding.profileImage)
        Glide.with(holder.binding.mainImage).load(userModel.urls?.small).into(holder.binding.mainImage)

        holder.binding.root.setOnClickListener {
            listener.onClick(userModel)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}