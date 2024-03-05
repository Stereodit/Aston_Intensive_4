package com.example.fragments.second_part.recycler

import androidx.recyclerview.widget.DiffUtil

object UserDiffUtil : DiffUtil.ItemCallback<UserItem>() {
    override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem.userPhone == newItem.userPhone
    }

    override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem == newItem
    }
}