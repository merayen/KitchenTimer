package net.merayen.kitchentimer.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.StorageTabViewModel

class StorageTab : Fragment() {

    companion object {
        fun newInstance() = StorageTab()
    }

    private lateinit var viewModel: StorageTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.storage_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StorageTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
