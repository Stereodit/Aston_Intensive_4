package com.example.fragments.first_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragments.R
import com.example.fragments.databinding.FragmentCBinding

class FragmentC : Fragment(R.layout.fragment_c) {

    private var binding: FragmentCBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCBinding.inflate(layoutInflater)

        parentFragmentManager.setFragmentResultListener(BUNDLE_REQUEST_KEY, this) { _, bundle ->
            binding!!.fragmentCIncomingText.text =
                bundle.getString(BUNDLE_MESSAGE_KEY) ?: "Sending error."
        }

        binding!!.fragmentCNextButton.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(R.id.fragment, FragmentD(), "fragmentD")
                addToBackStack("fragments_first_part")
                commit()
            }
        }

        binding!!.fragmentCPrevButton.setOnClickListener {
            parentFragmentManager.popBackStack()
            parentFragmentManager.popBackStack()
        }

        return binding!!.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("message", binding?.fragmentCIncomingText?.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding?.fragmentCIncomingText?.text =
            savedInstanceState?.getString("message") ?: getString(R.string.fragment_c_incoming_text)
    }
}