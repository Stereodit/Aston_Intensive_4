package com.example.fragments.first_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentBBinding

const val BUNDLE_REQUEST_KEY = "FROM_B_TO_C"
const val BUNDLE_MESSAGE_KEY = "MESSAGE"

class FragmentB : Fragment(R.layout.fragment_b) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBBinding.inflate(layoutInflater)

        binding.fragmentBNextButton.setOnClickListener {
            val message = "Hello, fragment C!"
            parentFragmentManager.setFragmentResult(
                BUNDLE_REQUEST_KEY,
                bundleOf(BUNDLE_MESSAGE_KEY to message)
            )

            parentFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(R.id.fragment, FragmentC(), "fragmentC")
                addToBackStack("fragments_first_part")
                commit()

            }
        }

        binding.fragmentBPrevButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}