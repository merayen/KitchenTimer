package net.merayen.kitchentimer.fragments.tabs.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.Location
import net.merayen.kitchentimer.data.NamedItem
import net.merayen.kitchentimer.fragments.common.SelectableList

class StorageTab : Fragment(), SelectableList.Handler {
    companion object {
        fun newInstance() = StorageTab()
    }

    private val viewModel by viewModels<StorageTabViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.storage_tab_fragment, container, false)

        // New storage item button
        view.findViewById<Button>(R.id.newButton).setOnClickListener {
            //viewModel.save(Location(null, ))
            val storageList = childFragmentManager.findFragmentById(R.id.storageList) as SelectableList?
            if (storageList != null) {
                val parent = storageList.itemSelected
                if (parent == 0)
                    showStorageEditor(null)
                else
                    showStorageEditor(null, parent)
            }
        }

        return view
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
        showStorageEditor(item.id)
    }

    /**
     * @param id If null, creates a new one
     * @param parent Set to null or an id if creating a storage
     */
    private fun showStorageEditor(id: Int?, parent: Int? = null) {
        val view = view ?: return
        val storageEdit = view.findViewById<FrameLayout>(R.id.storageEdit)
        childFragmentManager.commit {
            storageEdit.removeAllViews()
            add(
                R.id.storageEdit,
                StorageEditFragment::class.java,
                bundleOf("id" to id, "parent" to parent)
            )
        }
    }
}