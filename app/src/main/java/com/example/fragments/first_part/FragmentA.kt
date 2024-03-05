package com.example.fragments.first_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentABinding

class FragmentA : Fragment(R.layout.fragment_a) {

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
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

        val bind = FragmentABinding.inflate(layoutInflater)
        val fragmentB = FragmentB()

        bind.fragmentANextButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(R.id.fragment, fragmentB, "fragmentB")
                addToBackStack("fragments_first_part")
                commit()
            }
        }

        return bind.root
    }
}