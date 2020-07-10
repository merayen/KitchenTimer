package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.StorageTabViewModel

class StorageTab : Fragment() {
    companion object {
        fun newInstance() = StorageTab()
    }

    private val viewModel = viewModels<StorageTabViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.storage_tab_fragment, container, false)
    }
}
