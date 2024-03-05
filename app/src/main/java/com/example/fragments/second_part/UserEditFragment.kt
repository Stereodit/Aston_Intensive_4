package com.example.fragments.second_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentUserEditBinding
import com.example.fragments.second_part.UserListFragment.Companion.bundleKey
import com.example.fragments.second_part.UserListFragment.Companion.fromListRequestKey
import com.example.fragments.second_part.UserListFragment.Companion.toListRequestKey
import com.example.fragments.second_part.recycler.UserItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserEditFragment : Fragment(R.layout.fragment_user_edit) {

    private lateinit var binding: FragmentUserEditBinding

    private var userItem: UserItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserEditBinding.inflate(layoutInflater)

        parentFragmentManager.setFragmentResultListener(fromListRequestKey, this) { _, bundle ->
            userItem = bundle.getString(bundleKey)?.let { Json.decodeFromString(it) }

            if (userItem != null) {
                binding.userEditSurname.hint = userItem!!.userSurname
                binding.userEditName.hint = userItem!!.userName
                binding.userEditPhone.hint = userItem!!.userPhone
                binding.userEditImage.setImageResource(userItem!!.imageRes)
            } else Toast.makeText(binding.root.context,
                getString(R.string.receive_user_item_bundle_error), Toast.LENGTH_SHORT).show()
        }

        binding.userEditApplyButton.setOnClickListener {
            if (userItem != null) {
                val newUserItem = UserItem(
                    userId = userItem!!.userId,
                    userName =
                        if (binding.userEditName.text.toString() != "")
                            binding.userEditName.text.toString()
                        else userItem!!.userName,
                    userSurname =
                        if (binding.userEditSurname.text.toString() != "")
                            binding.userEditSurname.text.toString()
                        else userItem!!.userSurname,
                    userPhone =
                        if (binding.userEditPhone.text.toString() != "")
                            binding.userEditPhone.text.toString()
                        else userItem!!.userPhone,
                    imageRes = userItem!!.imageRes
                )

                parentFragmentManager.setFragmentResult(
                    toListRequestKey,
                    bundleOf(bundleKey to Json.encodeToString(newUserItem))
                )
            } else Toast.makeText(binding.root.context,
                getString(R.string.receive_user_item_bundle_error), Toast.LENGTH_SHORT).show()

            parentFragmentManager.popBackStack()
        }

        binding.userEditCancelButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("inputUserSurname", binding.userEditSurname.text.toString())
        outState.putString("inputUserName", binding.userEditName.text.toString())
        outState.putString("inputUserPhone", binding.userEditPhone.text.toString())

        outState.putString("userItem", Json.encodeToString(userItem))
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.userEditSurname.setText(savedInstanceState?.getString("inputUserSurname") ?: "")
        binding.userEditName.setText(savedInstanceState?.getString("inputUserName") ?: "")
        binding.userEditPhone.setText(savedInstanceState?.getString("inputUserPhone") ?: "")

        userItem = savedInstanceState?.getString("userItem")?.let { Json.decodeFromString(it) }
        if (userItem != null) {
            binding.userEditSurname.hint = userItem!!.userSurname
            binding.userEditName.hint = userItem!!.userName
            binding.userEditPhone.hint = userItem!!.userPhone
            binding.userEditImage.setImageResource(userItem!!.imageRes)
        }
    }
}