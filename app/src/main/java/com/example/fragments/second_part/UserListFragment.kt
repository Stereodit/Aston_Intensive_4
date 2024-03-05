package com.example.fragments.second_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fragments.FragmentApplication
import com.example.fragments.R
import com.example.fragments.databinding.FragmentUserListBinding
import com.example.fragments.second_part.recycler.UserAdapter
import com.example.fragments.second_part.recycler.UserItem

const val BUNDLE_FROM_LIST_REQUEST_KEY = "FROM_LIST"
const val BUNDLE_USER_ITEM_KEY = "USER_ITEM"

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private var adapter: UserAdapter? = null

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // TODO()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, onBackPressedCallback)

        val binding = FragmentUserListBinding.inflate(layoutInflater)

        val recycler = binding.recycler
        recycler.layoutManager = GridLayoutManager(binding.root.context, 2)

        if (adapter == null) {
            adapter = UserAdapter { onItemClick(it) }
            adapter!!.submitList(FragmentApplication.appUserList)
        }

        recycler.adapter = adapter

        parentFragmentManager.setFragmentResultListener(BUNDLE_TO_LIST_REQUEST_KEY, this) { _, bundle ->
            val newUserItem: UserItem? = bundle.serializable(BUNDLE_USER_ITEM_KEY) as UserItem?
            if (newUserItem != null) {
                adapter!!.submitList(adapter!!.currentList.toMutableList().also {
                    it.removeAt(newUserItem.userId)
                    it.add(newUserItem.userId, newUserItem)
                })
            } else Toast.makeText(binding.root.context,
                getString(R.string.receive_user_item_bundle_error), Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun onItemClick(item: UserItem) {
        parentFragmentManager.setFragmentResult(
            BUNDLE_FROM_LIST_REQUEST_KEY,
            bundleOf(BUNDLE_USER_ITEM_KEY to item)
        )
        parentFragmentManager.beginTransaction().apply {
            setReorderingAllowed(true)
            replace(R.id.fragment, UserEditFragment(), "userEditFragment")
            addToBackStack("fragments_second_part")
            commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        if (adapter != null)
            FragmentApplication.appUserList = adapter!!.currentList
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (adapter != null)
            FragmentApplication.appUserList = adapter!!.currentList
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        adapter!!.submitList(FragmentApplication.appUserList)
    }
}