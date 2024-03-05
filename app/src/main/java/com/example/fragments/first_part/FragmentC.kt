package com.example.fragments.first_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentCBinding

class FragmentC : Fragment(R.layout.fragment_c) {

    private var bind: FragmentCBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentCBinding.inflate(layoutInflater)
        val fragmentD = FragmentD()

        parentFragmentManager.setFragmentResultListener("partOneRequestKey", this) { _, bundle ->
            bind!!.fragmentCIncomingText.text = bundle.getString("bundleKeyFromBToC") ?: "Sending error."
        }

        bind!!.fragmentCNextButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(R.id.fragment, fragmentD, "fragmentD")
                addToBackStack("fragments_first_part")
                commit()
            }
        }

        bind!!.fragmentCPrevButton.setOnClickListener {
            parentFragmentManager.popBackStack()
            parentFragmentManager.popBackStack()
        }

        return bind!!.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("incomingText", bind?.fragmentCIncomingText?.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        bind?.fragmentCIncomingText?.text =
            savedInstanceState?.getString("incomingText") ?: getString(R.string.fragment_c_incoming_text)
    }
}