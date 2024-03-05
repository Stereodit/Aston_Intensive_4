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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bind = FragmentBBinding.inflate(layoutInflater)
        val fragmentC = FragmentC()

        bind.fragmentBNextButton.setOnClickListener {
            val incomingTextFromB = "Hello, fragment C!"
            parentFragmentManager.setFragmentResult(
                "partOneRequestKey",
                bundleOf("bundleKeyFromBToC" to incomingTextFromB)
            )

            parentFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(R.id.fragment, fragmentC, "fragmentC")
                addToBackStack("fragments_first_part")
                commit()

            }
        }

        bind.fragmentBPrevButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return bind.root
    }
}