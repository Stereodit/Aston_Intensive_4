package com.example.fragments

import android.app.Application
import com.example.fragments.second_part.data.UserCollection
import com.example.fragments.second_part.recycler.UserItem

class FragmentApplication : Application() {

    companion object {
        lateinit var appUserList: MutableList<UserItem>
    }

    override fun onCreate() {
        super.onCreate()
        appUserList = UserCollection.hardList.toMutableList()
    }
}