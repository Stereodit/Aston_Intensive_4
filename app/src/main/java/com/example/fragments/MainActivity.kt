package com.example.fragments

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.fragments.databinding.ActivityMainBinding
import com.example.fragments.first_part.FragmentA
import com.example.fragments.second_part.UserListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private enum class AppState {
        AppStart, FirstPart, SecondPart
    }

    private var appState = AppState.AppStart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null)
            appState = AppState.entries[savedInstanceState.getInt("appState")]

        refreshScreen(appState)

        binding.firstPartButton.setOnClickListener {
            refreshScreen(AppState.FirstPart)
            appState = AppState.FirstPart

            supportFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                add(R.id.fragment, FragmentA(), "fragmentA")
                addToBackStack("fragments_first_part")
                commit()
            }
        }

        binding.secondPartButton.setOnClickListener {
            refreshScreen(AppState.SecondPart)
            appState = AppState.SecondPart

            supportFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                add(R.id.fragment, UserListFragment(), "userListFragment")
                addToBackStack("fragments_second_part")
                commit()
            }
        }
    }

    private fun refreshScreen(appState: AppState) {
        when (appState) {
            AppState.AppStart -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.title = "Fragments"

                binding.fragment.isVisible = false
                binding.firstPartButton.isVisible = true
                binding.secondPartButton.isVisible = true
            }

            AppState.FirstPart -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.title = "Part 1"

                binding.fragment.isVisible = true
                binding.firstPartButton.isVisible = false
                binding.secondPartButton.isVisible = false
            }

            AppState.SecondPart -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.title = "Part 2"

                binding.fragment.isVisible = true
                binding.firstPartButton.isVisible = false
                binding.secondPartButton.isVisible = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            while (supportFragmentManager.backStackEntryCount != 0)
                supportFragmentManager.popBackStackImmediate()

            refreshScreen(AppState.AppStart)
            appState = AppState.AppStart
        }
        return true
    }

    override fun onStop() {
        supportFragmentManager.saveBackStack("fragments_first_part")
        supportFragmentManager.saveBackStack("fragments_second_part")

        super.onStop()
    }

    override fun onStart() {
        super.onStart()

        supportFragmentManager.restoreBackStack("fragments_first_part")
        supportFragmentManager.restoreBackStack("fragments_second_part")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("appState", appState.ordinal)
    }
}