package net.merayen.kitchentimer.fragments.tabs.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.NamedItem
import net.merayen.kitchentimer.fragments.common.SelectableList
import net.merayen.kitchentimer.viewmodels.StorageTabViewModel

class StorageTab : Fragment(), SelectableList.Handler {
    companion object {
        fun newInstance() = StorageTab()
    }

    private val viewModel by viewModels<StorageTabViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.storage_tab_fragment, container, false)
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        if (childFragment is SelectableList) {
            when (childFragment.id) {
                R.id.storageList -> {
                    viewModel.get().observe(viewLifecycleOwner, Observer {
                        childFragment.applyData(it)
                    })
                }
            }
        }
    }

    override fun onClick(item: NamedItem) {
        println("You clicked a Storage item! $item")
    }
}