package com.example.fragments.first_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentBBinding

class FragmentB : Fragment(R.layout.fragment_b) {

    companion object {
        const val requestKey = "fromBtoC"
        const val messageKey = "text"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBBinding.inflate(layoutInflater)

        binding.fragmentBNextButton.setOnClickListener {
            val textToFragmentC = "Hello, fragment C!"
            parentFragmentManager.setFragmentResult(
                requestKey,
                bundleOf(messageKey to textToFragmentC)
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