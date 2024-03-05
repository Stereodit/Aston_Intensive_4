package com.example.fragments.second_part.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fragments.databinding.ItemUserBinding

class UserAdapter(
    private val onClickAction: (UserItem) -> Unit
) : ListAdapter<UserItem, UserAdapter.UserViewHolder>(UserDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onClickAction(item) }
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {
        fun bind(item: UserItem) {
            binding.userSurname.text = item.userSurname
            binding.userName.text = item.userName
            binding.userPhone.text = item.userPhone
            binding.userImage.setImageResource(item.imageRes)
        }
    }
}